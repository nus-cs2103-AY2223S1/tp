package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutor.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutors.TUTOR1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;

public class JsonAdaptedTutorTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_QUALIFICATION = "pre-school";
    private static final String INVALID_INSTITUTION = "rock-school";
    private static final String VALID_NAME = TUTOR1.getName().toString();
    private static final String VALID_PHONE = TUTOR1.getPhone().toString();
    private static final String VALID_EMAIL = TUTOR1.getEmail().toString();
    private static final String VALID_ADDRESS = TUTOR1.getAddress().toString();
    private static final String VALID_QUALIFICATION = TUTOR1.getQualification().toString();
    private static final String VALID_INSTITUTION = TUTOR1.getInstitution().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TUTOR1.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTutorDetails_returnsTutor() throws Exception {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(TUTOR1);
        assertEquals(TUTOR1, tutor.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,VALID_QUALIFICATION,
                        VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
               VALID_QUALIFICATION, VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,VALID_QUALIFICATION,
                        VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
               VALID_QUALIFICATION, VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,VALID_QUALIFICATION,
                        VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
               VALID_QUALIFICATION, VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,VALID_QUALIFICATION,
                        VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
               VALID_QUALIFICATION, VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidQualification_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_QUALIFICATION,
                        VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = Qualification.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullSchool_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Qualification.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidInstitution_throwsIllegalValueException() {
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_QUALIFICATION,
                        INVALID_INSTITUTION, VALID_TAGS);
        String expectedMessage = Institution.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_nullInstitution_throwsIllegalValueException() {
        JsonAdaptedTutor tutor = new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_QUALIFICATION, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Institution.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutor::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTutor tutor =
                new JsonAdaptedTutor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,VALID_QUALIFICATION,
                        VALID_INSTITUTION, invalidTags);
        assertThrows(IllegalValueException.class, tutor::toModelType);
    }

}
