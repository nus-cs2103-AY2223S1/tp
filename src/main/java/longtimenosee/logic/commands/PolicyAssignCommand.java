package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_END;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_START;

import java.util.List;

import longtimenosee.commons.core.Messages;
import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.PolicyDate;
import longtimenosee.model.policy.Premium;


/**
 * Policy assign command, used to assign a policy to a client.
 */
public class PolicyAssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": assigns a policy to a client.\n"
            + "Parameters: "
            + "CLIENT_INDEX "
            + "POLICY_INDEX "
            + PREFIX_PREMIUM + "PREMIUM "
            + PREFIX_START + "START_DATE "
            + PREFIX_END + "END_DATE\n"
            + "Example: 1 1 "
            + PREFIX_PREMIUM + "200.00 "
            + PREFIX_START + "2020-12-10 "
            + PREFIX_END + "2021-12-15";

    public static final String MESSAGE_ASSIGN_POLICY_SUCCESS = "Assigned policy %1$s to Client: %2$s";

    public static final String MESSAGE_ASSIGN_PERSON_DUPLICATE = "Policy has already been assigned to Client: %2$s";

    private final Index targetPersonIndex;
    private final Index targetPolicyIndex;
    private final Premium premium;
    private final PolicyDate startDate;
    private final PolicyDate endDate;

    /**
     * Constructor for PolicyAssignCommand.
     * @param targetPersonIndex
     * @param targetPolicyIndex
     * @param premium
     * @param startDate
     * @param endDate
     */
    public PolicyAssignCommand(Index targetPersonIndex, Index targetPolicyIndex, Premium premium,
                               PolicyDate startDate, PolicyDate endDate) {
        this.targetPersonIndex = targetPersonIndex;
        this.targetPolicyIndex = targetPolicyIndex;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Policy> lastShownPolicyList = model.getFilteredPolicyList();

        if (targetPersonIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (targetPolicyIndex.getZeroBased() >= lastShownPolicyList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }

        Person personToAddTo = lastShownPersonList.get(targetPersonIndex.getZeroBased()); //gets the person to be added
        Policy policyToAdd = lastShownPolicyList.get(targetPolicyIndex.getZeroBased()); //gets the policy to be added
        boolean success = personToAddTo.addPolicy(new AssignedPolicy(
                policyToAdd, premium, startDate, endDate)); //add the policy
        //model.deletePerson(personToPin); optional because we don't alter the list
        return new CommandResult(String.format(success
                ? MESSAGE_ASSIGN_POLICY_SUCCESS : MESSAGE_ASSIGN_PERSON_DUPLICATE, policyToAdd.getTitle(),
                personToAddTo.getName()),
                false, false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyAssignCommand // instanceof handles nulls
                && targetPersonIndex.equals(((PolicyAssignCommand) other).targetPersonIndex) // state check
                && targetPolicyIndex.equals(((PolicyAssignCommand) other).targetPersonIndex) // state check
                && premium.equals(((PolicyAssignCommand) other).premium) // state check
                && startDate.equals(((PolicyAssignCommand) other).startDate) // state check
                && endDate.equals(((PolicyAssignCommand) other).endDate)); // state check
    }
}
