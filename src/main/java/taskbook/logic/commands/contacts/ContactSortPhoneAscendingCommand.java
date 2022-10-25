package taskbook.logic.commands.contacts;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts contacts by ascending phone number.
 */
public class ContactSortPhoneAscendingCommand extends ContactSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by ascending phone number order.\n"
            + "Contacts with no phone number are at the end of the list in no particular order.";
    public ContactSortPhoneAscendingCommand() {
        super((p1, p2) -> p1.compareByPhoneNumberAscendingTo(p2), MESSAGE_SORT_TYPE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedPersonList(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + super.messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactSortPhoneAscendingCommand); // instanceof handles nulls
    }
}
