package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.guest.model.Model.PREDICATE_SHOW_ALL_GUESTS;

import java.util.List;

import seedu.guest.commons.core.Messages;
import seedu.guest.commons.core.index.Index;
import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.Model;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.Guest;

/**
 * Updates the bill of an existing guest in the guest book.
 */
public class BillCommand extends Command {

    public static final String COMMAND_WORD = "bill";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds to the bill of the guest identified "
            + "by the index number used in the last guest listing.\n"
            + "To subtract from a bill, provide a negative value.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "b/BILL\n"
            + "Examples:\n"
            + COMMAND_WORD + " 1 b/59.99\n"
            + COMMAND_WORD + " 2 b/-10\n";

    public static final String MESSAGE_SUCCESS = "Updated bill of Guest: %1$s";
    public static final String MESSAGE_NEGATIVE_BILL = "Total bill cannot be negative";
    public static final String MESSAGE_TOO_LARGE_BILL = "Total bill cannot exceed 999,999,999,999.99";

    private final Index index;
    private final Bill bill;

    /**
     * @param index of the guest in the filtered guest list to edit the bill
     * @param bill of the guest to be updated to
     */
    public BillCommand(Index index, Bill bill) {
        requireAllNonNull(index, bill);

        this.index = index;
        this.bill = bill;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
        }

        Guest guestToEdit = lastShownList.get(index.getZeroBased());
        Bill editedBill;
        try {
            editedBill = guestToEdit.getBill().add(bill);
        } catch (IllegalArgumentException iae) {
            throw new CommandException(MESSAGE_TOO_LARGE_BILL);
        }

        if (editedBill.getValue() < 0) {
            throw new CommandException(MESSAGE_NEGATIVE_BILL);
        }

        Guest editedGuest = new Guest(
                guestToEdit.getName(), guestToEdit.getPhone(), guestToEdit.getEmail(), guestToEdit.getRoom(),
                guestToEdit.getDateRange(), guestToEdit.getNumberOfGuests(), guestToEdit.getIsRoomClean(),
                editedBill, guestToEdit.getRequest()
        );

        model.setGuest(guestToEdit, editedGuest);
        model.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedGuest));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BillCommand)) {
            return false;
        }

        // state check
        BillCommand e = (BillCommand) other;
        return index.equals(e.index)
                && bill.equals(e.bill);
    }
}
