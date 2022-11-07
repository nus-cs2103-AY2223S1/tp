package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandFailure;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_DEADLINE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.testutil.TypicalDeadlines;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDeadlineCommand}.
 */
public class DeleteDeadlineCommandTest {

    private Model model = new ModelManager(TypicalDeadlines.getTypicalFypManager(), new UserPrefs());
    private Student student = TypicalDeadlines.getTypicalStudent();

    @Test
    public void execute_validDeadline_success() {
        Deadline deadlineToDelete = student.getDeadlineList().getDeadlineByRank(INDEX_FIRST_DEADLINE.getZeroBased());
        StudentId validStudentId = student.getStudentId();
        DeleteDeadlineCommand deleteCommand = new DeleteDeadlineCommand(validStudentId,
                INDEX_FIRST_DEADLINE.getOneBased());

        String expectedMessage = String.format(DeleteDeadlineCommand.MESSAGE_DELETE_DEADLINE_SUCCESS, deadlineToDelete);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteDeadline(student, deadlineToDelete);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistDeadline_throwsCommandException() {
        Deadline deadlineToDelete = student.getDeadlineList().getDeadlineByRank(INDEX_FIRST_DEADLINE.getZeroBased());
        StudentId validStudentId = student.getStudentId();
        DeleteDeadlineCommand deleteCommand = new DeleteDeadlineCommand(validStudentId,
                INDEX_FIRST_DEADLINE.getOneBased() - 1);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.deleteDeadline(student, deadlineToDelete);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DEADLINE_RANK);
    }
    @Test
    public void equals() {
        DeleteDeadlineCommand deleteFirstCommand = new DeleteDeadlineCommand(new StudentId(VALID_STUDENT_ID_AMY), 1);
        DeleteDeadlineCommand deleteSecondCommand = new DeleteDeadlineCommand(new StudentId(VALID_STUDENT_ID_BOB), 2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDeadlineCommand deleteFirstCommandCopy = new DeleteDeadlineCommand(
                new StudentId(VALID_STUDENT_ID_AMY), 1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
