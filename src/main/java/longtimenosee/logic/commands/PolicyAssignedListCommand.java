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
 * Lists all persons in the address book to the user.
 */
public class PolicyAssignedListCommand extends Command {

    public static final String COMMAND_WORD = "listAssigned";

    public static final String MESSAGE_SUCCESS = "Listed all assigned policies for %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of assigned policies for a given person "
            + "Parameters: CLIENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index personIndex;

    public PolicyAssignedListCommand(Index personIndex) {
        this.personIndex = personIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        String display = "";

        requireNonNull(model);
        List<Person> filteredPersonList = model.getFilteredPersonList();
        if (personIndex.getZeroBased() >= filteredPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }
        Person personToList = filteredPersonList.get(personIndex.getZeroBased());
        Set<AssignedPolicy> assignedPolicySet = personToList.getAssignedPolicies();
        for (AssignedPolicy assignedPolicy: assignedPolicySet) {
            display += assignedPolicy + "\n";
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToList.getName()) + "\n" + display,
                false, true, false);
    }
}
