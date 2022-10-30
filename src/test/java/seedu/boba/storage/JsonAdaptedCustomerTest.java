package seedu.boba.storage;

import org.junit.jupiter.api.Test;
import seedu.boba.commons.exceptions.IllegalValueException;
import seedu.boba.model.customer.BirthdayMonth;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.boba.storage.JsonAdaptedCustomer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalCustomers.BENSON;

public class JsonAdaptedCustomerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_BIRTHDAY_MONTH = "two";
    private static final String INVALID_REWARD = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_BIRTHDAY_MONTH = BENSON.getBirthdayMonth().toString();
    private static final String VALID_REWARD = BENSON.getReward().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedCustomer person = new JsonAdaptedCustomer(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_BIRTHDAY_MONTH, VALID_REWARD, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCustomer person = new JsonAdaptedCustomer(null, VALID_PHONE, VALID_EMAIL,
                VALID_BIRTHDAY_MONTH, VALID_REWARD, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_BIRTHDAY_MONTH, VALID_REWARD, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer person = new JsonAdaptedCustomer(VALID_NAME, null, VALID_EMAIL,
                VALID_BIRTHDAY_MONTH, VALID_REWARD, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_BIRTHDAY_MONTH, VALID_REWARD, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer person = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, null,
                VALID_BIRTHDAY_MONTH, VALID_REWARD, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBirthdayMonth_throwsIllegalValueException() {
        JsonAdaptedCustomer person = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_BIRTHDAY_MONTH, VALID_REWARD, VALID_TAGS);
        String expectedMessage = BirthdayMonth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBirthdayMonth_throwsIllegalValueException() {
        JsonAdaptedCustomer person = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_REWARD, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BirthdayMonth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidReward_throwsIllegalValueException() {
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_BIRTHDAY_MONTH, INVALID_REWARD, VALID_TAGS);
        String expectedMessage = Reward.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullReward_throwsIllegalValueException() {
        JsonAdaptedCustomer person = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_BIRTHDAY_MONTH, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Reward.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_BIRTHDAY_MONTH, VALID_REWARD, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
