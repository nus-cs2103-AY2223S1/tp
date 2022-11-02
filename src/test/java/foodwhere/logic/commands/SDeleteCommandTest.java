package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.index.Index;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.stall.Stall;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SDeleteCommand}.
 */
public class SDeleteCommandTest {

    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Stall stallToDelete = model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());
        SDeleteCommand sDeleteCommand = new SDeleteCommand(TypicalIndexes.INDEX_FIRST_STALL);

        String expectedMessage = String.format(sDeleteCommand.MESSAGE_DELETE_STALL_SUCCESS, stallToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStall(stallToDelete);

        assertCommandSuccess(sDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        SDeleteCommand sDeleteCommand = new SDeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(sDeleteCommand, model, SDeleteCommand.MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);

        Stall stallToDelete = model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());
        SDeleteCommand sDeleteCommand = new SDeleteCommand(TypicalIndexes.INDEX_FIRST_STALL);

        String expectedMessage = String.format(sDeleteCommand.MESSAGE_DELETE_STALL_SUCCESS, stallToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStall(stallToDelete);
        showNoStall(expectedModel);

        CommandTestUtil.assertCommandSuccess(sDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_STALL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStallList().size());

        SDeleteCommand sDeleteCommand = new SDeleteCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(sDeleteCommand, model, SDeleteCommand.MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void equals() {
        SDeleteCommand deleteFirstCommand = new SDeleteCommand(TypicalIndexes.INDEX_FIRST_STALL);
        SDeleteCommand deleteSecondCommand = new SDeleteCommand(TypicalIndexes.INDEX_SECOND_STALL);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        SDeleteCommand deleteFirstCommandCopy = new SDeleteCommand(TypicalIndexes.INDEX_FIRST_STALL);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different stall -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStall(Model model) {
        model.updateFilteredStallList(p -> false);

        assertTrue(model.getFilteredStallList().isEmpty());
    }
}
