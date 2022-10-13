package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.prepareModel;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.Record;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteRecordCommand}.
 */
public class DeleteRecordCommandTest {

    private Model model = prepareModel();
    private Model expectedModel = prepareModel();

    @Test
    public void execute_validIndex_success() {
        // Deleting record with valid index
        Record recordToDelete = model.getFilteredRecordList().get(FIRST_INDEX.getZeroBased());
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(FIRST_INDEX);
        expectedModel.deleteRecord(recordToDelete);
        String expectedMessage = String.format(DeleteRecordCommand.MESSAGE_DELETE_RECORD_SUCCESS, recordToDelete);

        assertCommandSuccess(deleteRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecordList().size() + 1);
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
