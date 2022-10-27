package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.CsvAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Portfolio;
import seedu.address.model.tag.Tag;


public class CsvAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_INCOME = "3";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MEETING_DATE = "@2 Jan 2022";
    private static final String INVALID_TAG = "#friend";

    private static final Portfolio portfolio = BENSON.getPortfolio();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_INCOME = BENSON.getIncome().toString();
    private static final String VALID_MEETING_DATE = BENSON.getMeeting().getMeetingDate().toString();
    private static final String VALID_MEETING_LOCATION = BENSON.getMeeting().getMeetingLocation().toString();
    private static final List<Tag> VALID_TAGS = new ArrayList<>(BENSON.getTags());
    private static final String VALID_RISK = portfolio.getRisk().toString();
    private static final List<Plan> VALID_PLANS = new ArrayList<>(portfolio.getPlans());
    private static final List<Note> VALID_NOTE = new ArrayList<>(portfolio.getNotes());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        CsvAdaptedPerson person = new CsvAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidIncome_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_INCOME, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = Income.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullIncome_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Income.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMeetingDate_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
            VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INCOME, INVALID_MEETING_DATE,
            VALID_MEETING_LOCATION, VALID_TAGS, VALID_RISK, VALID_PLANS, VALID_NOTE);
        String expectedMessage = MeetingDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
