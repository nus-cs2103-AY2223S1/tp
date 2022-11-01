package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import org.junit.jupiter.api.Test;

public class JsonAdaptedBuyerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PRIORITY = "#friend";
    private static final String INVALID_PRICE_RANGE = "200";
    private static final String INVALID_DESIRED_CHARACTERISTICS = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_PRICE_RANGE = BENSON.getPriceRange().get().toString();
    private static final String VALID_DESIRED_CHARACTERISTICS = BENSON.getDesiredCharacteristics().toString();
    private static final String VALID_TAGS = BENSON.getPriority().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedBuyer person = new JsonAdaptedBuyer(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_validPersonDetailsWithEmptyPriceRange_returnsPerson() throws Exception {
        JsonAdaptedBuyer person = new JsonAdaptedBuyer(GEORGE);
        assertEquals(GEORGE, person.toModelType());
    }

    @Test
    public void toModelType_validPersonDetailsWithEmptyDesiredCharacteristics_returnsPerson() throws Exception {
        JsonAdaptedBuyer person = new JsonAdaptedBuyer(FIONA);
        assertEquals(FIONA, person.toModelType());
    }

    @Test
    public void toModelType_validPersonDetailsWithEmptyPriceRangeAndDesiredCharacteristics_returnsPerson()
            throws Exception {
        JsonAdaptedBuyer person1 = new JsonAdaptedBuyer(DANIEL);
        assertEquals(DANIEL, person1.toModelType());
        JsonAdaptedBuyer person2 = new JsonAdaptedBuyer(ELLE);
        assertEquals(ELLE, person2.toModelType());
    }

    //    @Test
    //    public void toModelType_invalidName_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person =
    //                new JsonAdaptedBuyer(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
    //                        VALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_nullName_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person = new JsonAdaptedBuyer(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
    //                VALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidPhone_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person =
    //                new JsonAdaptedBuyer(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
    //                        VALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_nullPhone_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person = new JsonAdaptedBuyer(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
    //                VALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidEmail_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person =
    //                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_PRICE_RANGE,
    //                        VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_nullEmail_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person = new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
    //                VALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidAddress_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person =
    //                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
    //                        VALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_nullAddress_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person = new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
    //                VALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidPriceRange_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person =
    //                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
    //                        INVALID_PRICE_RANGE, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = PriceRange.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_nullPriceRange_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person = new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
    //                null, VALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PriceRange.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidDesiredCharacteristics_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person =
    //                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
    //                        VALID_PRICE_RANGE, INVALID_DESIRED_CHARACTERISTICS, VALID_TAGS);
    //        String expectedMessage = Characteristics.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_nullDesiredCharacteristics_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person = new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
    //                VALID_PRICE_RANGE, null, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
    //                Characteristics.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidPriority_throwsIllegalValueException() {
    //        JsonAdaptedBuyer person =
    //                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_PRICE_RANGE,
    //                        VALID_DESIRED_CHARACTERISTICS, INVALID_PRIORITY);
    //        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }

}
