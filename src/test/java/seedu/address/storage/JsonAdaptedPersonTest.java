package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKTAG_HIGH;
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
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.ClientTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_RISKTAG = "jump";
    private static final String INVALID_PLANTAG = "sit";
    private static final String INVALID_CLIENTTAG = "stand";
    private static final String INVALID_INCOME = "asd301222";
    private static final String INVALID_MONTHLY = "asd201";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATETIME = "21/05/2023 2359";

    private static final String INVALID_LOCATION = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_INCOME = BENSON.getIncome().toString().substring(1);
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_RISKTAG = BENSON.getRiskTag().toString();
    private static final String VALID_PLANTAG = BENSON.getPlanTag().tagName;
    private static final String VALID_CLIENTTAG = BENSON.getClientTag().toString();
    private static final String VALID_MONTHLY = BENSON.getMonthly().toString().substring(1);
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAppointment> VALID_APPOINTMENTS = BENSON.getAppointments().stream()
            .map(JsonAdaptedAppointment::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_INCOME,
                VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_INCOME,
                VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_invalidRiskTag_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                VALID_MONTHLY, INVALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = RiskTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRiskTag_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_INCOME, VALID_MONTHLY, null, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RiskTag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullIncome_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IncomeLevel.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidIncome_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = IncomeLevel.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMonthly_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_INCOME , null, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Monthly.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMonthly_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                        INVALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Monthly.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG, VALID_PLANTAG, VALID_CLIENTTAG, invalidTags, VALID_APPOINTMENTS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPlanTag_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG_HIGH, INVALID_PLANTAG,
                        VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = PlanTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPlanTag_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_INCOME, VALID_MONTHLY, VALID_RISKTAG_HIGH, null, VALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PlanTag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidClientTag_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME,
                        VALID_MONTHLY, VALID_RISKTAG_HIGH, "SAVINGS Plan",
                        INVALID_CLIENTTAG, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = ClientTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullClientTag_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_INCOME, VALID_MONTHLY, VALID_RISKTAG_HIGH, "SAVINGS Plan", null, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClientTag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
