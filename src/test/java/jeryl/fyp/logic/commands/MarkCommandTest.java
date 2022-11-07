package jeryl.fyp.logic.commands;

import static jeryl.fyp.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_STATUS_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_STATUS_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.exceptions.StudentNotFoundException;
import jeryl.fyp.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemarkCommand}.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());

    @Test
    public void execute_validStudentIdToIp_success() {
        ProjectStatus projectStatus = new ProjectStatus("IP");
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit)
                .withProjectStatus(projectStatus.projectStatus).build();
        StudentId validStudentId = studentToEdit.getStudentId();

        MarkCommand markCommand = new MarkCommand(validStudentId, projectStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PROJECT_STATUS_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validStudentIdToYts_success() {
        ProjectStatus projectStatus = new ProjectStatus("YTS");
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit)
                .withProjectStatus(projectStatus.projectStatus).build();
        StudentId validStudentId = studentToEdit.getStudentId();

        MarkCommand markCommand = new MarkCommand(validStudentId, projectStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PROJECT_STATUS_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validStudentIdToDone_success() {
        ProjectStatus projectStatus = new ProjectStatus("DONE");
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit)
                .withProjectStatus(projectStatus.projectStatus).build();
        StudentId validStudentId = studentToEdit.getStudentId();

        MarkCommand markCommand = new MarkCommand(validStudentId, projectStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PROJECT_STATUS_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentStudentId_throwsStudentNotFoundException() {
        ProjectStatus projectStatus = new ProjectStatus("IP");
        StudentId invalidStudentId = new StudentId(VALID_STUDENT_ID_AMY);
        MarkCommand markCommand = new MarkCommand(invalidStudentId, projectStatus);
        assertThrows(StudentNotFoundException.class, MESSAGE_STUDENT_NOT_FOUND, () -> markCommand.execute(model));
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
