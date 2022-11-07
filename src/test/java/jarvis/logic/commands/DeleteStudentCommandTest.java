package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jarvis.logic.commands.CommandTestUtil.showStudentAtIndex;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jarvis.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static jarvis.testutil.TypicalStudents.getTypicalStudentBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class DeleteStudentCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        Model expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of student book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentBook().getStudentList().size());

        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(INDEX_FIRST_STUDENT);
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(INDEX_FIRST_STUDENT);
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
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
