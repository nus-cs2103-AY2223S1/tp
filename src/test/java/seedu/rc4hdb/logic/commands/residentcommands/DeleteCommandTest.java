package seedu.rc4hdb.logic.commands.residentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.showResidentAtIndex;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_SECOND_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.resident.Resident;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Resident residentToDelete = model.getFilteredResidentList().get(INDEX_FIRST_RESIDENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_RESIDENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RESIDENT_SUCCESS, residentToDelete);

        ModelManager expectedModel = new ModelManager(model.getResidentBook(), new VenueBook(), new UserPrefs());
        expectedModel.deleteResident(residentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredResidentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_RESIDENT_WITH_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showResidentAtIndex(model, INDEX_FIRST_RESIDENT);

        Resident residentToDelete = model.getFilteredResidentList().get(INDEX_FIRST_RESIDENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_RESIDENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RESIDENT_SUCCESS, residentToDelete);

        Model expectedModel = new ModelManager(model.getResidentBook(), new VenueBook(), new UserPrefs());
        expectedModel.deleteResident(residentToDelete);
        showNoResident(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showResidentAtIndex(model, INDEX_FIRST_RESIDENT);

        Index outOfBoundIndex = INDEX_SECOND_RESIDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResidentBook().getResidentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NO_RESIDENT_WITH_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_RESIDENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_RESIDENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_RESIDENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoResident(Model model) {
        model.updateFilteredResidentList(p -> false);

        assertTrue(model.getFilteredResidentList().isEmpty());
    }
}
