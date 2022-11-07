package seedu.classify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.classify.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.classify.commons.exceptions.IllegalValueException;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;

public class JsonAdaptedStudentTest {
    private static final String INVALID_STUDENT_NAME = "R@chel";
    private static final String INVALID_PARENT_NAME = "John#";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "rachel@@gmail.com";
    private static final String INVALID_ID = "21A";
    private static final String INVALID_CLASS = "17#21";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_STUDENT_NAME = BENSON.getStudentName().toString();
    private static final String VALID_PARENT_NAME = BENSON.getParentName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_CLASS = BENSON.getClassName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedExam> VALID_TAGS = BENSON.getExams().stream()
            .map(JsonAdaptedExam::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(INVALID_STUDENT_NAME, VALID_ID, VALID_CLASS,
                        VALID_PARENT_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_ID, VALID_CLASS, VALID_PARENT_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidParentName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, VALID_CLASS,
                        INVALID_PARENT_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullParentName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, VALID_CLASS, null,
                VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, VALID_CLASS, VALID_PARENT_NAME,
                        INVALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, VALID_CLASS, VALID_PARENT_NAME,
                null, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_STUDENT_NAME, INVALID_ID, VALID_CLASS, VALID_PARENT_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, null, VALID_CLASS, VALID_PARENT_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidClass_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, INVALID_CLASS, VALID_PARENT_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Class.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullClass_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, null,
                VALID_PARENT_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Class.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, VALID_CLASS, VALID_PARENT_NAME,
                        VALID_PHONE, INVALID_EMAIL, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, VALID_CLASS, VALID_PARENT_NAME,
                VALID_PHONE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedExam> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedExam(INVALID_TAG));
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_ID, VALID_CLASS, VALID_PARENT_NAME,
                        VALID_PHONE, VALID_EMAIL, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
