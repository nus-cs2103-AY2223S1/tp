package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecordAtIndex;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Record;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteRecordCommand}.
 */
public class DeleteRecordCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredRecordList_success() {
        model.setFilteredRecordList(BENSON);
        Record recordToDelete = model.getFilteredRecordList().get(FIRST_INDEX.getZeroBased());
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(FIRST_INDEX);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteRecord(recordToDelete);
        String expectedMessage = String.format(DeleteRecordCommand.MESSAGE_DELETE_RECORD_SUCCESS, recordToDelete);
        assertCommandSuccess(deleteRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredRecordList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecordList().size() + 1);
        model.setRecordListDisplayed(true);
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(outOfBoundIndex);

        assertCommandFailure(deleteRecordCommand, model, Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredRecordList_success() {
        showRecordAtIndex(model, FIRST_INDEX);

        Record recordToDelete = model.getFilteredRecordList().get(FIRST_INDEX.getZeroBased());
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteRecordCommand.MESSAGE_DELETE_RECORD_SUCCESS, recordToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteRecord(recordToDelete);
        showNoRecord(expectedModel);

        assertCommandSuccess(deleteRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredRecordList_throwsCommandException() {
        showRecordAtIndex(model, FIRST_INDEX);

        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(outOfBoundIndex);

        assertCommandFailure(deleteRecordCommand, model, Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteRecordCommand firstDeleteRecordCommand = new DeleteRecordCommand(FIRST_INDEX);
        DeleteRecordCommand secondDeleteRecordCommand = new DeleteRecordCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(firstDeleteRecordCommand.equals(firstDeleteRecordCommand));

        // same values -> returns true
        DeleteRecordCommand deleteFirstCommandCopy = new DeleteRecordCommand(FIRST_INDEX);
        assertTrue(firstDeleteRecordCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstDeleteRecordCommand.equals(1));

        // null -> returns false
        assertFalse(firstDeleteRecordCommand.equals(null));

        // different person -> returns false
        assertFalse(firstDeleteRecordCommand.equals(secondDeleteRecordCommand));
    }
    /**
     * Updates {@code model}'s filtered record list to show no records.
     */
    private void showNoRecord(Model model) {
        model.updateFilteredRecordList(p -> false);

        assertTrue(model.getFilteredRecordList().isEmpty());
    }
}
