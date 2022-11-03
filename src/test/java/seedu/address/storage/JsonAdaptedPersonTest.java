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
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {

    private static final String INVALID_CATEGORY = "n";
    private static final String INVALID_ID = " one";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GENDER = "f";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DATESLOT = " : :2022.1.11 4.00PM:-1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_CATEGORY = BENSON.getCategory().toString();
    private static final Long VALID_UID = BENSON.getUid().getUid();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();

    private static final List<JsonAdaptedDateSlot> VALID_DATESLOT = ((Patient) BENSON).getDatesSlots()
            .stream().map(JsonAdaptedDateSlot::new).collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedHomeVisit> EMPTY_HOME_VISITS = new ArrayList<>();
    private static final List<JsonAdaptedDate> EMPTY_DATE_LIST = new ArrayList<>();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        System.out.println("Hello");
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, INVALID_CATEGORY, VALID_GENDER,
                VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS, EMPTY_DATE_LIST, EMPTY_DATE_LIST,
                VALID_NAME, VALID_PHONE, VALID_EMAIL);

        String expectedMessage = Category.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, null, VALID_GENDER,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS,
                EMPTY_HOME_VISITS, EMPTY_DATE_LIST, EMPTY_DATE_LIST,
                VALID_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, INVALID_NAME, VALID_CATEGORY, VALID_GENDER,
                VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS, EMPTY_DATE_LIST, EMPTY_DATE_LIST,
                VALID_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, null, VALID_CATEGORY, VALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, INVALID_GENDER,
                VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);

        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, VALID_GENDER,
                INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, VALID_GENDER,
                null, VALID_EMAIL, VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, VALID_GENDER,
                VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY,
                VALID_GENDER, VALID_PHONE, null, VALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, VALID_GENDER,
                VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);

        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, VALID_GENDER,
                VALID_PHONE, VALID_EMAIL, null, VALID_DATESLOT, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDatesTimes_throwsIllegalValueException() {
        List<JsonAdaptedDateSlot> invalidDatesTimes = new ArrayList<>(VALID_DATESLOT);
        invalidDatesTimes.add(new JsonAdaptedDateSlot(INVALID_DATESLOT));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY, VALID_GENDER,
                VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, invalidDatesTimes, VALID_TAGS, EMPTY_HOME_VISITS,
                EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_UID, VALID_NAME, VALID_CATEGORY,
                VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATESLOT, invalidTags,
                EMPTY_HOME_VISITS, EMPTY_DATE_LIST, EMPTY_DATE_LIST, VALID_NAME, VALID_PHONE, VALID_EMAIL);

        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
