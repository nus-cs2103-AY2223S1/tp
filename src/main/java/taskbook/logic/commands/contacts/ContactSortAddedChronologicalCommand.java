package taskbook.logic.commands.contacts;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the list of persons by the order in which they were added into Task Book.
 */
public class ContactSortAddedChronologicalCommand extends ContactSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by time added into Task Book";
    public ContactSortAddedChronologicalCommand() {
        super(null, MESSAGE_SORT_TYPE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.resetSortedPersonList();
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + super.messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactSortAddedChronologicalCommand); // instanceof handles nulls
    }
}
