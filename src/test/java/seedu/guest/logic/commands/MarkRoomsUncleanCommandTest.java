package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
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

        MarkRoomsUncleanCommand markRoomsUncleanCommand = new MarkRoomsUncleanCommand();
        String expectedMessage = String.format(MarkRoomsUncleanCommand.MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(getTypicalGuestBook(), new UserPrefs());

        ObservableList<Guest> filteredGuestList = model.getFilteredGuestList();
        int guestListSize = filteredGuestList.size();

        for (int index = 0; index < guestListSize; index++) {
            Guest guestInFilteredList = filteredGuestList.get(index);
            Guest editedGuest = new GuestBuilder(guestInFilteredList).withIsRoomClean(validIsRoomUnclean).build();
            expectedModel.setGuest(guestInFilteredList, editedGuest);
        }

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
