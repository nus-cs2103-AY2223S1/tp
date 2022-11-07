package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static jarvis.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.InvalidAssessmentException;
import jarvis.model.Assessment;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.UserPrefs;

class MasteryCheckCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());
    private Index validIndex = Index.fromOneBased(1);
    private Assessment validMcAssessment = Assessment.MC1;
    private boolean validIsPass = true;

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MasteryCheckCommand(null,
                validMcAssessment, validIsPass));

        assertThrows(NullPointerException.class, () -> new MasteryCheckCommand(validIndex,
                null, validIsPass));

        assertThrows(NullPointerException.class, () -> new MasteryCheckCommand(null,
                null, validIsPass));
    }

    @Test
    void constructor_assessmentNotMc_throwsInvalidAssessmentException() {
        Assessment invalidMcAssessment = Assessment.RA1;
        assertThrows(InvalidAssessmentException.class, () ->
                new MasteryCheckCommand(validIndex, invalidMcAssessment, validIsPass));
    }

    @Test
    void execute_validArguments_success() {
        Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());
        Student targetStudent = expectedModel.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        targetStudent.updateMark(Assessment.MC1, 1);
        MasteryCheckCommand masteryCheckCommand = new MasteryCheckCommand(INDEX_FIRST_PERSON,
                Assessment.MC1, true);
        String expectedMessage = String.format(MasteryCheckCommand.MESSAGE_SUCCESS, Assessment.MC1, targetStudent);

        assertCommandSuccess(masteryCheckCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        MasteryCheckCommand masteryCheckCommand = new MasteryCheckCommand(outOfBoundsIndex,
                validMcAssessment, validIsPass);

        assertCommandFailure(masteryCheckCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
