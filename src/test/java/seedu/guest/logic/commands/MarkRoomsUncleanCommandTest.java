package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;

import org.junit.jupiter.api.Test;

import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.Guest;
import seedu.guest.testutil.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkRoomsUncleanCommand.
 */
public class MarkRoomsUncleanCommandTest {

    private Model model = new ModelManager(getTypicalGuestBook(), new UserPrefs());

    @Test
    public void execute_markRoomsUncleanCommand_success() {
        String validIsRoomUnclean = "no";

        Guest guestInFilteredList0 = model.getFilteredGuestList().get(0);
        Guest guestInFilteredList1 = model.getFilteredGuestList().get(1);
        Guest guestInFilteredList2 = model.getFilteredGuestList().get(2);
        Guest guestInFilteredList3 = model.getFilteredGuestList().get(3);
        Guest guestInFilteredList4 = model.getFilteredGuestList().get(4);
        Guest guestInFilteredList5 = model.getFilteredGuestList().get(5);
        Guest guestInFilteredList6 = model.getFilteredGuestList().get(6);

        Guest editedGuest0 = new GuestBuilder(guestInFilteredList0).withIsRoomClean(validIsRoomUnclean).build();
        Guest editedGuest1 = new GuestBuilder(guestInFilteredList1).withIsRoomClean(validIsRoomUnclean).build();
        Guest editedGuest2 = new GuestBuilder(guestInFilteredList2).withIsRoomClean(validIsRoomUnclean).build();
        Guest editedGuest3 = new GuestBuilder(guestInFilteredList3).withIsRoomClean(validIsRoomUnclean).build();
        Guest editedGuest4 = new GuestBuilder(guestInFilteredList4).withIsRoomClean(validIsRoomUnclean).build();
        Guest editedGuest5 = new GuestBuilder(guestInFilteredList5).withIsRoomClean(validIsRoomUnclean).build();
        Guest editedGuest6 = new GuestBuilder(guestInFilteredList6).withIsRoomClean(validIsRoomUnclean).build();

        MarkRoomsUncleanCommand markRoomsUncleanCommand = new MarkRoomsUncleanCommand();

        String expectedMessage = String.format(MarkRoomsUncleanCommand.MESSAGE_SUCCESS);

        Model expectedModel = new ModelManager(getTypicalGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestInFilteredList0, editedGuest0);
        expectedModel.setGuest(guestInFilteredList1, editedGuest1);
        expectedModel.setGuest(guestInFilteredList2, editedGuest2);
        expectedModel.setGuest(guestInFilteredList3, editedGuest3);
        expectedModel.setGuest(guestInFilteredList4, editedGuest4);
        expectedModel.setGuest(guestInFilteredList5, editedGuest5);
        expectedModel.setGuest(guestInFilteredList6, editedGuest6);

        assertCommandSuccess(markRoomsUncleanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        MarkRoomsUncleanCommand markRoomsUncleanCommand = new MarkRoomsUncleanCommand();

        // same object -> returns true
        assertTrue(markRoomsUncleanCommand.equals(markRoomsUncleanCommand));

        // different types -> returns false
        assertFalse(markRoomsUncleanCommand.equals(1));

        // null -> returns false
        assertFalse(markRoomsUncleanCommand.equals(null));

    }
}
