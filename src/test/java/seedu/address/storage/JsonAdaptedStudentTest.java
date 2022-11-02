package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Telegram;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEGRAM = "hello";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_RESPONSE = "abc";
    private static final String INVALID_ATTENDANCE = "a";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_RESPONSE = BENSON.getResponse().toString();
    private static final String VALID_ATTENDANCE = BENSON.getAttendance().toString();
    private static final String VALID_HELPTAG = BENSON.getHelpTag().toString();

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_TELEGRAM, VALID_EMAIL, VALID_RESPONSE, VALID_ATTENDANCE,
                        VALID_HELPTAG);
        String expectedMessage = StuName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_TELEGRAM, VALID_EMAIL, VALID_RESPONSE,
                VALID_ATTENDANCE, VALID_HELPTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StuName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_TELEGRAM, VALID_EMAIL, VALID_RESPONSE, VALID_ATTENDANCE,
                        VALID_HELPTAG);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_RESPONSE,
                VALID_ATTENDANCE, VALID_HELPTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Telegram.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAM, INVALID_EMAIL, VALID_RESPONSE, VALID_ATTENDANCE,
                        VALID_HELPTAG);
        String expectedMessage = StuEmail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAM, null, VALID_RESPONSE,
                VALID_ATTENDANCE, VALID_HELPTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StuEmail.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidResponse_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAM, VALID_EMAIL, INVALID_RESPONSE, VALID_ATTENDANCE,
                        VALID_HELPTAG);
        String expectedMessage = Response.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullResponse_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAM, VALID_EMAIL, null,
                VALID_ATTENDANCE, VALID_HELPTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Response.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidAttendance_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAM, VALID_EMAIL, VALID_RESPONSE, INVALID_ATTENDANCE,
                        VALID_HELPTAG);
        String expectedMessage = Attendance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAM, VALID_EMAIL, VALID_RESPONSE,
                null, VALID_HELPTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

}
