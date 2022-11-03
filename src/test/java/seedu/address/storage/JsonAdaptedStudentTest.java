package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Phone;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tutorial.TutorialName;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ID = "123";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM = "h.i";
    private static final String INVALID_TUTORIAL = "";
    private static final String INVALID_MODULE = "";
    private static final String INVALID_ATTENDANCE = "a";
    private static final String INVALID_PARTICIPATION = "a";
    private static final String INVALID_GRADE = "z";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();
    private static final String VALID_TUTORIAL = BENSON.getTutorialName().toString();
    private static final String VALID_MODULE = BENSON.getModuleCode().toString();
    private static final String VALID_ATTENDANCE = BENSON.getAttendance().toString();
    private static final String VALID_PARTICIPATION = BENSON.getParticipation().toString();
    private static final String VALID_GRADE = BENSON.getGrade().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(INVALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_ID, VALID_PHONE, VALID_EMAIL,
                VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, INVALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_ID, null, VALID_EMAIL,
                VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, INVALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, null,
                VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, INVALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, null, VALID_PHONE, VALID_EMAIL,
                VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        INVALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                null, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, INVALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                VALID_TELEGRAM, null, VALID_TUTORIAL, VALID_ATTENDANCE,
                VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTutorial_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, INVALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = TutorialName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTutorial_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                VALID_TELEGRAM, VALID_MODULE, null, VALID_ATTENDANCE,
                VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAttendance_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, INVALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = Attendance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_invalidParticipation_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        INVALID_PARTICIPATION, VALID_GRADE, VALID_TAGS);
        String expectedMessage = Participation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, INVALID_GRADE, VALID_TAGS);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_MODULE, VALID_TUTORIAL, VALID_ATTENDANCE,
                        VALID_PARTICIPATION, VALID_GRADE, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
