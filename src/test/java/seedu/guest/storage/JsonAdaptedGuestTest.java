package seedu.guest.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.guest.storage.JsonAdaptedGuest.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.guest.testutil.Assert.assertThrows;
import static seedu.guest.testutil.TypicalGuests.BENSON;

import org.junit.jupiter.api.Test;

import seedu.guest.commons.exceptions.IllegalValueException;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;

public class JsonAdaptedGuestTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DATE_RANGE = "13/09/22 - 13/09/22";
    private static final String INVALID_NUMBER_OF_GUESTS = "-1";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_DATE_RANGE = BENSON.getDateRange().toString();
    private static final String VALID_NUMBER_OF_GUESTS = BENSON.getNumberOfGuests().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedGuest person = new JsonAdaptedGuest(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_DATE_RANGE, VALID_NUMBER_OF_GUESTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(null, VALID_PHONE, VALID_EMAIL, VALID_DATE_RANGE,
                VALID_NUMBER_OF_GUESTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_DATE_RANGE, VALID_NUMBER_OF_GUESTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, null, VALID_EMAIL, VALID_DATE_RANGE,
                VALID_NUMBER_OF_GUESTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_DATE_RANGE, VALID_NUMBER_OF_GUESTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, VALID_PHONE, null, VALID_DATE_RANGE,
                VALID_NUMBER_OF_GUESTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateRange_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_DATE_RANGE,
                VALID_NUMBER_OF_GUESTS);
        String expectedMessage = DateRange.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateRange_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_NUMBER_OF_GUESTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateRange.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNumberOfGuests_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_DATE_RANGE, INVALID_NUMBER_OF_GUESTS);
        String expectedMessage = NumberOfGuests.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNumberOfGuests_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_DATE_RANGE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NumberOfGuests.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
