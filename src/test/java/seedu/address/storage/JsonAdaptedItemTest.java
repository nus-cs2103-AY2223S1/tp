package seedu.address.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.CUCUMBERS;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "Po|a|oes\\";
    private static final String INVALID_QUANTITY = "1/2";
    private static final String INVALID_UNIT = "||//";
    private static final String INVALID_BOUGHT_DATE = "september11";
    private static final String INVALID_EXPIRY_DATE = "september11";

    private static final String VALID_NAME = CUCUMBERS.getName().toString();
    private static final String VALID_QUANTITY = CUCUMBERS.getQuantity().toString();
    private static final String VALID_UNIT = CUCUMBERS.getUnit().toString();
    private static final String VALID_BOUGHT_DATE = CUCUMBERS.getBoughtDate().toString();
    private static final String VALID_EXPIRY_DATE = CUCUMBERS.getExpiryDate().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedItem person = new JsonAdaptedItem(CUCUMBERS);
        assertEquals(CUCUMBERS, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItem person =
            new JsonAdaptedItem(INVALID_NAME, VALID_QUANTITY, VALID_UNIT, VALID_BOUGHT_DATE, VALID_EXPIRY_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItem person = new JsonAdaptedItem(null,
            VALID_QUANTITY,
            VALID_UNIT,
            VALID_BOUGHT_DATE,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedItem person =
            new JsonAdaptedItem(VALID_NAME,
                INVALID_QUANTITY,
                VALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedItem person = new JsonAdaptedItem(
            VALID_NAME,
            null,
            VALID_UNIT,
            VALID_BOUGHT_DATE,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedItem person =
            new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                INVALID_UNIT,
                VALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedItem person = new JsonAdaptedItem(VALID_NAME,
            VALID_QUANTITY,
            null,
            VALID_BOUGHT_DATE,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedItem person =
            new JsonAdaptedItem(VALID_NAME,
                VALID_QUANTITY,
                VALID_UNIT,
                INVALID_BOUGHT_DATE,
                VALID_EXPIRY_DATE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedItem person = new JsonAdaptedItem(VALID_NAME,
            VALID_QUANTITY,
            VALID_UNIT,
            null,
            VALID_EXPIRY_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    //@Test
    //public void toModelType_invalidTags_throwsIllegalValueException() {
    //    List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
    //    invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
    //    JsonAdaptedItem person =
    //            new JsonAdaptedItem(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
    //    assertThrows(IllegalValueException.class, person::toModelType);
    //}

}
