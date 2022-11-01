package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeliverers.BENSON;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Phone;



public class JsonAdaptedDelivererTest {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Deliverer 's %s field is missing!";

    private static final String INVALID_PERSON_CATEGORY = "Empty";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_PERSON_CATEGORY = BENSON.getPersonCategory().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<String> VALID_IDS = BENSON.getOrders().stream().map(Objects::toString)
            .collect(Collectors.toList());
    private static final String VALID_LOCATION = BENSON.getLocation().toString();

    //    private static final List<JsonAdaptedOrder> VALID_ORDERS = BENSON.getOrders().stream()
    //            .map(JsonAdaptedOrder::new)
    //            .collect(Collectors.toList());

    @Test
    public void toModelType_validDelivererDetails_returnsDeliverer() throws Exception {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidPersonCategory_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(INVALID_PERSON_CATEGORY, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_IDS);
        String expectedMessage = PersonCategory.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPersonCategory_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(null, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PersonCategory.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDeliverer deliverer = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, INVALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_IDS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, deliverer::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, VALID_NAME, INVALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_IDS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, VALID_NAME, VALID_PHONE,
                INVALID_EMAIL, VALID_ADDRESS, VALID_IDS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, INVALID_ADDRESS, VALID_IDS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedDeliverer person = new JsonAdaptedDeliverer(VALID_PERSON_CATEGORY, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, null, VALID_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
