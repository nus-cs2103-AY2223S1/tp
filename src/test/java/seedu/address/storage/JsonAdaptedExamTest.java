package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExam.MISSING_EXAM_DATE;
import static seedu.address.storage.JsonAdaptedExam.MISSING_EXAM_DESCRIPTION;
import static seedu.address.storage.JsonAdaptedExam.MISSING_MODULE_CODE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.EXAMONE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.ModuleCode;

public class JsonAdaptedExamTest {
    private static final String INVALID_DATE = "20-20-20";
    private static final String INVALID_EXAM_DESCRIPTION = "";
    private static final String INVALID_MODULE_CODE = "20";

    private static final String VALID_DATE = EXAMONE.getExamDate().toString();
    private static final String VALID_EXAM_DESCRIPTION = EXAMONE.getDescription().toString();
    private static final String VALID_MODULE_CODE = EXAMONE.getModule().toString();

    @Test
    public void toModelType_validExamDetails_returnsExam() throws IllegalValueException {
        JsonAdaptedExam exam = new JsonAdaptedExam(EXAMONE);
        assertEquals(EXAMONE, exam.toModelType());
    }

    @Test
    public void toModelType_invalidExamDate_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_EXAM_DESCRIPTION, VALID_MODULE_CODE, INVALID_DATE);
        String expectedMessage = ExamDate.DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_invalidNonExistingExamDate_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_EXAM_DESCRIPTION, VALID_MODULE_CODE, "29-02-2023");
        String expectedMessage = ExamDate.DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_nullExamDate_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_EXAM_DESCRIPTION, VALID_MODULE_CODE, null);
        String expectedMessage = String.format(MISSING_EXAM_DATE);
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_invalidExamDescription_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(INVALID_EXAM_DESCRIPTION, VALID_MODULE_CODE, VALID_DATE);
        String expectedMessage = ExamDescription.DESCRIPTION_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_nullExamDescription_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(null, VALID_MODULE_CODE, VALID_DATE);
        String expectedMessage = String.format(MISSING_EXAM_DESCRIPTION);
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }


    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_EXAM_DESCRIPTION, INVALID_MODULE_CODE, VALID_DATE);
        String expectedMessage = ModuleCode.MODULE_CODE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedExam exam = new JsonAdaptedExam(VALID_EXAM_DESCRIPTION, null, VALID_DATE);
        String expectedMessage = String.format(MISSING_MODULE_CODE);
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

}
