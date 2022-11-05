package seedu.studmap.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.studmap.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.studmap.testutil.Assert.assertThrows;
import static seedu.studmap.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R\nchel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CLASS = "T01!";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_MODULE = BENSON.getModule().toString();
    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_GIT = BENSON.getGitString();
    private static final String VALID_HANDLE = BENSON.getHandleString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAttendance> VALID_ATTENDANCES = BENSON.getAttendances().stream()
            .map(JsonAdaptedAttendance::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAssignment> VALID_ASSIGNMENTS = BENSON.getAssignments().stream()
            .map(JsonAdaptedAssignment::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedParticipation> VALID_PARTICIPATIONS = BENSON.getParticipations().stream()
                                                                               .map(JsonAdaptedParticipation::new)
                                                                               .collect(Collectors.toList());

    @Test
    public void toModelType_validstudentDetails_returnsstudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                        VALID_ID, VALID_GIT, VALID_HANDLE,
                        VALID_TAGS, VALID_ATTENDANCES, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                VALID_ID, VALID_GIT, VALID_HANDLE, VALID_TAGS,
                VALID_ATTENDANCES, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_MODULE,
                        VALID_ID, VALID_GIT, VALID_HANDLE,
                        VALID_TAGS, VALID_ATTENDANCES, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_MODULE,
                VALID_ID, VALID_GIT, VALID_HANDLE,
                VALID_TAGS, VALID_ATTENDANCES, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_MODULE,
                        VALID_ID, VALID_GIT, VALID_HANDLE,
                        VALID_TAGS, VALID_ATTENDANCES, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_MODULE,
                VALID_ID, VALID_GIT, VALID_HANDLE,
                VALID_TAGS, VALID_ATTENDANCES, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                        VALID_ID, VALID_GIT, VALID_HANDLE, invalidTags,
                        VALID_ATTENDANCES, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidAttendances_throwsIllegalValueException() {
        List<JsonAdaptedAttendance> invalidAttendances = new ArrayList<>(VALID_ATTENDANCES);
        invalidAttendances.add(new JsonAdaptedAttendance(INVALID_CLASS + ":Present"));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                        VALID_ID, VALID_GIT, VALID_HANDLE,
                        VALID_TAGS, invalidAttendances, VALID_ASSIGNMENTS, VALID_PARTICIPATIONS);

        assertThrows(IllegalValueException.class, student::toModelType);
    }
}
