package jeryl.fyp.storage;

import static jeryl.fyp.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalStudents.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.Name;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.StudentId;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "+1651234D";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PROJECT_NAME = "$@CS2103 SE!?";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_PROJECT_NAME = BENSON.getProjectName().toString();
    private static final String VALID_PROJECT_STATUS = BENSON.getProjectStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, INVALID_STUDENT_ID, VALID_EMAIL, VALID_PROJECT_NAME,
                        VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_STUDENT_ID, VALID_EMAIL,
                VALID_PROJECT_NAME, VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_STUDENT_ID, VALID_EMAIL, VALID_PROJECT_NAME,
                        VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL,
                VALID_PROJECT_NAME, VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_STUDENT_ID, INVALID_EMAIL, VALID_PROJECT_NAME,
                        VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_STUDENT_ID, null,
                VALID_PROJECT_NAME, VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidProjectName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_STUDENT_ID, VALID_EMAIL, INVALID_PROJECT_NAME,
                        VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = ProjectName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullProjectName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_STUDENT_ID, VALID_EMAIL,
                null, VALID_PROJECT_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_STUDENT_ID, VALID_EMAIL,
                        VALID_PROJECT_NAME, VALID_PROJECT_STATUS, invalidTags);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

}
