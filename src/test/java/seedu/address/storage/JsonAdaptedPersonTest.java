package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tutorial;

public class JsonAdaptedPersonTest {
    private static final String INVALID_OCCUPATION = "stutaprof";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TUTORIAL = "A";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_SOCIAL = "12345";
    private static final String INVALID_GROUP = "two words";

    private static final String VALID_OCCUPATION = BENSON.getOccupation().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TUTORIAL = BENSON.getTutorial().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_SOCIAL = BENSON.getSocial().toString();
    private static final List<JsonAdaptedGroup> VALID_GROUPS = BENSON.getGroups().stream()
            .map(JsonAdaptedGroup::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidOccupation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_OCCUPATION, VALID_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_TUTORIAL, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL,
                        VALID_GROUPS);
        String expectedMessage = Occupation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_OCCUPATION, INVALID_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_TUTORIAL, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL,
                        VALID_GROUPS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_OCCUPATION, null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TUTORIAL, VALID_TAGS, VALID_SOCIAL, VALID_GROUPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                        INVALID_PHONE, VALID_EMAIL, VALID_TUTORIAL, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL,
                        VALID_GROUPS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                null, VALID_EMAIL, VALID_TUTORIAL, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL, VALID_GROUPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                        VALID_PHONE, INVALID_EMAIL, VALID_TUTORIAL, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL,
                        VALID_GROUPS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                VALID_PHONE, null, VALID_TUTORIAL, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL, VALID_GROUPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTutorial_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                        VALID_PHONE, INVALID_EMAIL, INVALID_TUTORIAL, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL,
                        VALID_GROUPS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTutorial_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_ADDRESS, VALID_TAGS, VALID_SOCIAL, VALID_GROUPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tutorial.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_TUTORIAL, INVALID_ADDRESS, VALID_TAGS, VALID_SOCIAL,
                        VALID_GROUPS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_TUTORIAL, null, VALID_TAGS, VALID_SOCIAL, VALID_GROUPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_OCCUPATION, VALID_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_TUTORIAL, VALID_ADDRESS, invalidTags, VALID_SOCIAL,
                        VALID_GROUPS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
