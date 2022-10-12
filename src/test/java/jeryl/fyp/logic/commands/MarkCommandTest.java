package jeryl.fyp.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandFailure;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;

import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.StudentId;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());

    final StudentId studentId = new StudentId("A0123456G");

    //TODO - add more test for MarkCommand

    @Test
    public void equals() {
        final MarkCommand standardCommand = new MarkCommand(studentId,
                new ProjectStatus(VALID_STATUS_AMY));

        // same values -> returns true
        MarkCommand commandWithSameValues = new MarkCommand(studentId,
                new ProjectStatus(VALID_STATUS_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different studentId -> returns false
        final StudentId diffStudentId = new StudentId("A1234567F");
        assertFalse(standardCommand.equals(new MarkCommand(diffStudentId,
                new ProjectStatus(VALID_STATUS_AMY))));

        // different status -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(studentId,
                new ProjectStatus(VALID_STATUS_BOB))));
    }
}
