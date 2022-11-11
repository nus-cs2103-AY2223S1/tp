package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jarvis.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static jarvis.testutil.TypicalStudents.getTypicalStudentBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.model.Assessment;
import jarvis.model.GradeProfile;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.UserPrefs;

class GradeCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_validIndexValidGradeProfile_success() {
        Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());
        GradeProfile validGradeProfile = new GradeProfile();
        validGradeProfile.grade(Assessment.RA1, 16);
        Student gradedStudent = expectedModel.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        gradedStudent.updateGrades(validGradeProfile);
        GradeCommand gradeCommand = new GradeCommand(INDEX_FIRST_STUDENT, validGradeProfile);
        String expectedMessage = String.format(GradeCommand.MESSAGE_SUCCESS, gradedStudent);

        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexValidGradeProfile_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        GradeProfile validGradeProfile = new GradeProfile();
        GradeCommand gradeCommand = new GradeCommand(outOfBoundsIndex, validGradeProfile);

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void testEquals() {
        GradeCommand gradeFirstCommand = new GradeCommand(INDEX_FIRST_STUDENT, new GradeProfile());
        GradeCommand gradeSecondCommand = new GradeCommand(INDEX_SECOND_STUDENT, new GradeProfile());

        // same object -> returns true
        assertTrue(gradeFirstCommand.equals(gradeFirstCommand));

        // same fields -> returns true
        GradeCommand gradeFirstCommandCopy = new GradeCommand(INDEX_FIRST_STUDENT, new GradeProfile());
        assertTrue(gradeFirstCommand.equals(gradeFirstCommandCopy));

        // different types -> returns false
        assertFalse(gradeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(gradeFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(gradeFirstCommand.equals(gradeSecondCommand));
    }
}
