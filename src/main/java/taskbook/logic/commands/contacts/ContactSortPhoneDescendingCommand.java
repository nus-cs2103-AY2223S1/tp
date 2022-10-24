package taskbook.logic.commands.contacts;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts contacts by descending phone number.
 */
public class ContactSortPhoneDescendingCommand extends ContactSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by descending phone number order.\n"
            + "Contacts with no phone number are at the end of the list in no particular order.";
    public ContactSortPhoneDescendingCommand() {
        super((p1, p2) -> p1.compareByPhoneNumberDescendingTo(p2), MESSAGE_SORT_TYPE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedPersonList(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactSortPhoneDescendingCommand); // instanceof handles nulls
    }
}
