package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.guest.model.Model.PREDICATE_SHOW_ALL_GUESTS;

import java.util.List;

import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.Model;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Request;
import seedu.guest.model.guest.Room;

/**
 * Changes isRoomClean field of all guests in guest book to "no".
 */
public class MarkRoomsUncleanCommand extends Command {

    public static final String COMMAND_WORD = "markroomsunclean";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes all guests' isRoomClean statuses to \"no\".\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Marked all guests' isRoomClean statuses as \"no\".";

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
     * Creates and returns a {@code Guest} with the isRoomClean set to "no".
     */
    private static Guest createGuestWithRoomUnclean(Guest guestToEdit) {
        assert guestToEdit != null;

        String roomCleanStatus = "no";

        Name updatedName = guestToEdit.getName();
        Phone updatedPhone = guestToEdit.getPhone();
        Email updatedEmail = guestToEdit.getEmail();
        Room updatedRoom = guestToEdit.getRoom();
        DateRange updatedDateRange = guestToEdit.getDateRange();
        NumberOfGuests updatedNumberOfGuests = guestToEdit.getNumberOfGuests();
        IsRoomClean updatedIsRoomClean = new IsRoomClean(roomCleanStatus);
        Bill updatedBill = guestToEdit.getBill();
        Request updatedRequest = guestToEdit.getRequest();
        return new Guest(updatedName, updatedPhone, updatedEmail, updatedRoom, updatedDateRange,
                updatedNumberOfGuests, updatedIsRoomClean, updatedBill, updatedRequest);
    }

}
