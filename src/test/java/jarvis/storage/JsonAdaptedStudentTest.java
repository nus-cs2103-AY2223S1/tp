package jarvis.storage;

import static jarvis.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalStudents.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jarvis.model.GradeProfile;
import jarvis.model.StudentName;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_MATRIC_NUM = BENSON.getMatricNum().toString();
    private static final GradeProfile VALID_GRADE_PROFILE = new GradeProfile();


    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalArgumentException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_MATRIC_NUM, VALID_GRADE_PROFILE);
        String expectedMessage = StudentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalArgumentException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent((String) null, VALID_MATRIC_NUM, VALID_GRADE_PROFILE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentName.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, student::toModelType);
    }
}
