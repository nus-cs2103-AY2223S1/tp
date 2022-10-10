package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.logic.commands.CommandTestUtil.showGuestAtIndex;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;
import static seedu.guest.testutil.TypicalIndexes.INDEX_FIRST_GUEST;
import static seedu.guest.testutil.TypicalIndexes.INDEX_SECOND_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guest.commons.core.Messages;
import seedu.guest.commons.core.index.Index;
import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.Guest;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalGuestBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Guest guestToDelete = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_GUEST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_GUEST_SUCCESS, guestToDelete);

        ModelManager expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.deleteGuest(guestToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGuestList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);

        Guest guestToDelete = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_GUEST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_GUEST_SUCCESS, guestToDelete);

        Model expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.deleteGuest(guestToDelete);
        showNoGuest(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);

        Index outOfBoundIndex = INDEX_SECOND_GUEST;
        // ensures that outOfBoundIndex is still in bounds of guest book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getGuestBook().getGuestList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_GUEST);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_GUEST);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_GUEST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different guest -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGuest(Model model) {
        model.updateFilteredGuestList(p -> false);

        assertTrue(model.getFilteredGuestList().isEmpty());
    }
}
