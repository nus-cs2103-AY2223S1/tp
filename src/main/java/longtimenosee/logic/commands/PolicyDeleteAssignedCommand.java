package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import longtimenosee.commons.core.Messages;
import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.AssignedPolicy;


/**
 * Policy assign command, used to assign a policy to a client.
 */
public class PolicyDeleteAssignedCommand extends Command {

    public static final String COMMAND_WORD = "deleteAssigned";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes assigned policy from a person. "
            + "Parameters: "
            + "CLIENT_INDEX "
            + "ASSIGNED_POLICY_INDEX "
            + "\nExample: " + COMMAND_WORD + " 1 1";

    public static final String MESSAGE_ASSIGN_POLICY_SUCCESS = "Deleted assigned policy %1$s from Client: %2$s";
    public static final String MESSAGE_ASSIGN_POLICY_FAIL = "Unable to delete assigned policy %1$s from Client: %2$s";

    private final Index targetPersonIndex;
    private final Index targetPolicyIndex;

    /**
     * Constructor for PolicyAssignCommand.
     * @param targetPersonIndex
     * @param targetPolicyIndex
     */
    public PolicyDeleteAssignedCommand(Index targetPersonIndex, Index targetPolicyIndex) {
        this.targetPersonIndex = targetPersonIndex;
        this.targetPolicyIndex = targetPolicyIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetPersonIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDeleteFrom = lastShownPersonList.get(targetPersonIndex.getZeroBased());
        Set<AssignedPolicy> assignedPolicySet = personToDeleteFrom.getAssignedPolicies();

        if (targetPolicyIndex.getZeroBased() >= assignedPolicySet.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }

        AssignedPolicy[] assignedPolicyArray = new AssignedPolicy[assignedPolicySet.size()];
        assignedPolicySet.toArray(assignedPolicyArray);
        AssignedPolicy assignedPolicy = assignedPolicyArray[targetPolicyIndex.getZeroBased()];
        boolean success = personToDeleteFrom.removePolicy(assignedPolicy);
        return new CommandResult(String.format(success
                ? MESSAGE_ASSIGN_POLICY_SUCCESS : MESSAGE_ASSIGN_POLICY_FAIL, assignedPolicy.getPolicy().getTitle(),
                personToDeleteFrom.getName()),
                false, false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyDeleteAssignedCommand // instanceof handles nulls
                && targetPersonIndex.equals(((PolicyDeleteAssignedCommand) other).targetPersonIndex) // state check
                && targetPolicyIndex.equals(((PolicyDeleteAssignedCommand) other).targetPersonIndex)); // state check
    }
}
