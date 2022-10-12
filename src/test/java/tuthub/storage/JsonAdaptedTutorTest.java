package tuthub.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tuthub.storage.JsonAdaptedTutor.MISSING_FIELD_MESSAGE_FORMAT;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import tuthub.commons.exceptions.IllegalValueException;
import tuthub.model.tutor.Address;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.Year;

public class JsonAdaptedTutorTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MODULE = "CS100";
    private static final String INVALID_YEAR = "0";
    private static final String INVALID_STUDENTID = "C1234567L";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_MODULE = BENSON.getModule().toString();
    private static final String VALID_YEAR = BENSON.getYear().toString();
    private static final String VALID_STUDENTID = BENSON.getStudentId().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_COMMENT = BENSON.getComment().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTutorDetails_returnsTutor() throws Exception {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(BENSON);
        assertEquals(BENSON, tutor.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                    VALID_MODULE, VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(null, VALID_PHONE, VALID_EMAIL,
            VALID_MODULE, VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                    VALID_MODULE, VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, null, VALID_EMAIL,
            VALID_MODULE, VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_MODULE, VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                INVALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = Year.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullYear_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
            new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                null, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Year.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                    VALID_MODULE, VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, null,
            VALID_MODULE, VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                        VALID_YEAR, INVALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_MODULE, VALID_YEAR, null, VALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                    VALID_YEAR, VALID_STUDENTID, INVALID_ADDRESS, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL,
            VALID_MODULE, VALID_YEAR, VALID_STUDENTID, null, VALID_COMMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                    VALID_YEAR, VALID_STUDENTID, VALID_ADDRESS, VALID_COMMENT, invalidTags);
        assertThrows(IllegalValueException.class, tutor::toModelType);
    }

}
