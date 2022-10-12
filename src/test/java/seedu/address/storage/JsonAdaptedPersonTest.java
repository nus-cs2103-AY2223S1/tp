package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

<<<<<<< HEAD
=======
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

>>>>>>> master
public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_STATUS = "App!@";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
<<<<<<< HEAD
    //    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
    //            .map(JsonAdaptedTag::new)
    //            .collect(Collectors.toList());
    //    to be changed.
=======
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_STATUS = BENSON.getStatus().toString();
>>>>>>> master

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        //        JsonAdaptedPerson person =
        //                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        //        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_STATUS,
                        VALID_NOTE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        // String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        // assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_STATUS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // JsonAdaptedPerson person =
        //         new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        // String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        // assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_STATUS,
                        VALID_NOTE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        // String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        // assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_STATUS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // JsonAdaptedPerson person =
        //         new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        // String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        // assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_STATUS,
                        VALID_NOTE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
        // String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        // assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_TAGS, VALID_STATUS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // JsonAdaptedPerson person =
        //         new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
        // String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        // assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS, VALID_STATUS,
                        VALID_NOTE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
        // String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        // assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_TAGS, VALID_STATUS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
<<<<<<< HEAD
        // to be implemented.
        // List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        // invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        // JsonAdaptedPerson person =
        //         new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        // assertThrows(IllegalValueException.class, person::toModelType);
=======
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags, VALID_STATUS,
                        VALID_NOTE);
        assertThrows(IllegalValueException.class, person::toModelType);
>>>>>>> master
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, INVALID_STATUS,
                        VALID_NOTE);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
