package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandFailure;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.logic.commands.CommandTestUtil.showStudentAtIndex;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class DeleteStudentCommandTest {

    private Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());

    @Test
    public void execute_validStudentIdUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId validStudentId = studentToDelete.getStudentId();
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(validStudentId);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentStudentIdUnfilteredList_throwsCommandException() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId nonexistentStudentId = new StudentId(VALID_STUDENT_ID_AMY);
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(nonexistentStudentId);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void execute_validStudentIdFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId validStudentId = studentToDelete.getStudentId();
        DeleteStudentCommand deleteCommand = new DeleteStudentCommand(validStudentId);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentStudentIdFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId nonexistentStudentId = new StudentId(VALID_STUDENT_ID_AMY);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(nonexistentStudentId);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandFailure(deleteStudentCommand, model, Messages.MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(new StudentId(VALID_STUDENT_ID_AMY));
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(new StudentId(VALID_STUDENT_ID_BOB));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(new StudentId(VALID_STUDENT_ID_AMY));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
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
