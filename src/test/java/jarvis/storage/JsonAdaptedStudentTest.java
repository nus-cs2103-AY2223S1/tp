package jarvis.storage;

import static jarvis.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalStudents.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.StudentName;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final boolean VALID_PASS_MC = false;


    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PASS_MC, VALID_PASS_MC);
        String expectedMessage = StudentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent((String) null, VALID_PASS_MC, VALID_PASS_MC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }
}
