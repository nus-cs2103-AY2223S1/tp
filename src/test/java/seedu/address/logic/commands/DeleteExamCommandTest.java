package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteExamCommand}.
 */
public class DeleteExamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Exam examToDelete = model.getFilteredExamList().get(FIRST_INDEX.getZeroBased());
        Task taskToUnlink = model.getFilteredTaskList().get(SECOND_INDEX.getZeroBased()).linkTask(examToDelete);
        assertTrue(taskToUnlink.isLinked());
        assertTrue(taskToUnlink.getExam().equals(examToDelete));

        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(FIRST_INDEX);

        String expectedMessage = String.format(DeleteExamCommand.MESSAGE_DELETE_EXAM_SUCCESS, examToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteExam(examToDelete);

        assertCommandSuccess(deleteExamCommand, model, expectedMessage, expectedModel);
        assertFalse(model.getFilteredTaskList().get(SECOND_INDEX.getZeroBased()).isLinked());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExamList().size() + 1);
        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(outOfBoundIndex);

        assertCommandFailure(deleteExamCommand, model, String.format(
                Messages.MESSAGE_INVALID_EXAM_INDEX_TOO_LARGE, model.getFilteredExamList().size() + 1));
    }

    @Test
    public void execute_noExamInList_throwsCommandException() {
        model.updateFilteredExamList(e -> false);
        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(FIRST_INDEX);

        assertCommandFailure(deleteExamCommand, model, DeleteExamCommand.MESSAGE_NO_EXAM_IN_LIST);
    }

    @Test
    public void equals() {
        DeleteExamCommand deleteFirstExamCommand = new DeleteExamCommand(FIRST_INDEX);
        DeleteExamCommand deleteSecondExamCommand = new DeleteExamCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(deleteFirstExamCommand.equals(deleteFirstExamCommand));

        // same values -> returns true
        DeleteExamCommand deleteFirstExamCommandCopy = new DeleteExamCommand(FIRST_INDEX);
        assertTrue(deleteFirstExamCommand.equals(deleteFirstExamCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstExamCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstExamCommand.equals(null));

        // different exam -> returns false
        assertFalse(deleteFirstExamCommand.equals(deleteSecondExamCommand));
    }
}
