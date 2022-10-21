package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_STATUS_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_STATUS_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandFailure;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemarkCommand}.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());

    @Test
    public void execute_validStudentId_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId validStudentId = studentToDelete.getStudentId();
        DeleteCommand deleteCommand = new DeleteCommand(validStudentId);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentStudentId_throwsCommandException() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId nonexistentStudentId = new StudentId(VALID_STUDENT_ID_AMY);
        DeleteCommand deleteCommand = new DeleteCommand(nonexistentStudentId);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(new StudentId(VALID_STUDENT_ID_AMY),
                new ProjectStatus(VALID_PROJECT_STATUS_AMY));
        MarkCommand markSecondCommand = new MarkCommand(new StudentId(VALID_STUDENT_ID_BOB),
                new ProjectStatus(VALID_PROJECT_STATUS_BOB));

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(new StudentId(VALID_STUDENT_ID_AMY),
                new ProjectStatus(VALID_PROJECT_STATUS_AMY));
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(new ClearCommand()));

        // different studentId -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));

        // different status -> returns false
        assertFalse(markFirstCommand.equals(new MarkCommand(new StudentId(VALID_STUDENT_ID_AMY),
                new ProjectStatus(VALID_PROJECT_STATUS_BOB))));
    }
}
