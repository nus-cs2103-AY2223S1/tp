package seedu.guest.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.model.Model;
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
public class ResetRoomCleanCommand extends Command {

    public static final String COMMAND_WORD = "resetRoomClean";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes all guest RoomClean status in current list to \"no\".\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Reset all guests' Room Clean status in current list to \"no\".";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();

        Guest guestToEdit;
        Guest editedGuest;

        for (int index = 0; index < lastShownList.size(); index++) {
            guestToEdit = lastShownList.get(index);
            editedGuest = createGuestWithResetRoomClean(guestToEdit);
            model.setGuest(guestToEdit, editedGuest);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a {@code Guest} with the isRoomClean set to no.
     */
    private static Guest createGuestWithResetRoomClean(Guest guestToEdit) {
        assert guestToEdit != null;

        Name updatedName = guestToEdit.getName();
        Phone updatedPhone = guestToEdit.getPhone();
        Email updatedEmail = guestToEdit.getEmail();
        DateRange updatedDateRange = guestToEdit.getDateRange();
        NumberOfGuests updatedNumberOfGuests = guestToEdit.getNumberOfGuests();
        IsRoomClean updatedIsRoomClean = new IsRoomClean("no");
        return new Guest(updatedName, updatedPhone, updatedEmail, updatedDateRange,
                updatedNumberOfGuests, updatedIsRoomClean);
    }

}
