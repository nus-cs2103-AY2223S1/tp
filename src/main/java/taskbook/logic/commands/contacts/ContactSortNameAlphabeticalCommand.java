package taskbook.logic.commands.contacts;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the list of persons by the alphabetical order of their names.
 */
public class ContactSortNameAlphabeticalCommand extends ContactSortCommand {
    public ContactSortNameAlphabeticalCommand() {
        super((p1, p2) -> p1.compareByNameAlphabeticalTo(p2));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedPersonList(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactSortNameAlphabeticalCommand); // instanceof handles nulls
    }
}
