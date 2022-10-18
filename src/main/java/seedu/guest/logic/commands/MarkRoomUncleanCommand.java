package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.Model;
import static seedu.guest.model.Model.PREDICATE_SHOW_ALL_GUESTS;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;


/**
 * Changes isRoomClean field of all guest in current list to "no".
 */
public class MarkRoomUncleanCommand extends Command {

    public static final String COMMAND_WORD = "markRoomUnclean";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes all guest RoomClean statuses to \"no\".\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Mark all guests' Room Clean statuses as \"no\".";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
        List<Guest> completeGuestList = model.getFilteredGuestList();

        Guest guestToEdit;
        Guest editedGuest;

        for (int index = 0; index < completeGuestList.size(); index++) {
            guestToEdit = completeGuestList.get(index);
            editedGuest = createGuestWithRoomUnclean(guestToEdit);
            model.setGuest(guestToEdit, editedGuest);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a {@code Guest} with the isRoomClean set to no.
     */
    private static Guest createGuestWithRoomUnclean(Guest guestToEdit) {
        assert guestToEdit != null;

        Name updatedName = guestToEdit.getName();
        Phone updatedPhone = guestToEdit.getPhone();
        Email updatedEmail = guestToEdit.getEmail();
        DateRange updatedDateRange = guestToEdit.getDateRange();
        NumberOfGuests updatedNumberOfGuests = guestToEdit.getNumberOfGuests();
        IsRoomClean updatedIsRoomClean = new IsRoomClean("no");
        Bill updatedBill = guestToEdit.getBill();
        return new Guest(updatedName, updatedPhone, updatedEmail, updatedDateRange,
                updatedNumberOfGuests, updatedIsRoomClean, updatedBill);
    }

}
