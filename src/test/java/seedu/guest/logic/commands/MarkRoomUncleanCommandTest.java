package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_IS_ROOM_CLEAN_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;

import org.junit.jupiter.api.Test;

import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.Guest;
import seedu.guest.testutil.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkRoomUncleanCommand.
 */
public class MarkRoomUncleanCommandTest {

    private Model model = new ModelManager(getTypicalGuestBook(), new UserPrefs());

    @Test
    public void execute_markRoomUncleanCommand_success() {
        Guest guestInFilteredList0 = model.getFilteredGuestList().get(0);
        Guest guestInFilteredList1 = model.getFilteredGuestList().get(1);
        Guest guestInFilteredList2 = model.getFilteredGuestList().get(2);
        Guest guestInFilteredList3 = model.getFilteredGuestList().get(3);
        Guest guestInFilteredList4 = model.getFilteredGuestList().get(4);
        Guest guestInFilteredList5 = model.getFilteredGuestList().get(5);
        Guest guestInFilteredList6 = model.getFilteredGuestList().get(6);

        Guest editedGuest0 = new GuestBuilder(guestInFilteredList0).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();
        Guest editedGuest1 = new GuestBuilder(guestInFilteredList1).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();
        Guest editedGuest2 = new GuestBuilder(guestInFilteredList2).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();
        Guest editedGuest3 = new GuestBuilder(guestInFilteredList3).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();
        Guest editedGuest4 = new GuestBuilder(guestInFilteredList4).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();
        Guest editedGuest5 = new GuestBuilder(guestInFilteredList5).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();
        Guest editedGuest6 = new GuestBuilder(guestInFilteredList6).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();

        MarkRoomUncleanCommand markRoomUncleanCommand = new MarkRoomUncleanCommand();

        String expectedMessage = String.format(MarkRoomUncleanCommand.MESSAGE_SUCCESS);

        Model expectedModel = new ModelManager(getTypicalGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestInFilteredList0, editedGuest0);
        expectedModel.setGuest(guestInFilteredList1, editedGuest1);
        expectedModel.setGuest(guestInFilteredList2, editedGuest2);
        expectedModel.setGuest(guestInFilteredList3, editedGuest3);
        expectedModel.setGuest(guestInFilteredList4, editedGuest4);
        expectedModel.setGuest(guestInFilteredList5, editedGuest5);
        expectedModel.setGuest(guestInFilteredList6, editedGuest6);

        assertCommandSuccess(markRoomUncleanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        MarkRoomUncleanCommand markRoomUncleanCommand = new MarkRoomUncleanCommand();

        // same object -> returns true
        assertTrue(markRoomUncleanCommand.equals(markRoomUncleanCommand));

        // different types -> returns false
        assertFalse(markRoomUncleanCommand.equals(1));

        // null -> returns false
        assertFalse(markRoomUncleanCommand.equals(null));

    }
}
