package taskbook.logic.commands.contacts;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the list of persons by the reverse alphabetical order of their names.
 */
public class ContactSortNameReverseAlphabeticalCommand extends ContactSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by name in reverse alphabetical order";
    public ContactSortNameReverseAlphabeticalCommand() {
        super((p1, p2) -> p1.compareByNameReverseAlphabeticalTo(p2), MESSAGE_SORT_TYPE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedPersonList(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + super.messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactSortNameReverseAlphabeticalCommand); // instanceof handles nulls
    }
}
