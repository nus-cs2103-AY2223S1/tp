package taskbook.logic.commands.contacts;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.commands.tasks.TaskSortAddedChronologicalCommand;
import taskbook.model.Model;

/**
 * Sorts the list of persons by the order in which they were added into Task Book.
 */
public class ContactSortAddedChronologicalCommand extends ContactSortCommand {
    public ContactSortAddedChronologicalCommand() {
        super(null);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.resetSortedPersonList();
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactSortAddedChronologicalCommand); // instanceof handles nulls
    }
}
