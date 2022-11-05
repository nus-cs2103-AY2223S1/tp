package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.Date;
import seedu.address.model.event.EventSortField;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonSortField;
import seedu.address.model.person.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String INVALID_GENDER = "unknown";
    private static final String INVALID_DOB = "1.1.2000";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_GENDER = "Female";
    private static final String VALID_DOB = "12/12/2000";

    private static final String FUTURE_DOB = "12/12/3000";

    private static final String WHITESPACE = " \t\r\n";


    //=========== parseIndex() Tests ==========================================================================

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }


    //=========== parsePersonSortField() Tests ================================================================

    @Test
    public void parsePersonSortField_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePersonSortField((String) null));
    }

    @Test
    public void parsePersonSortField_invalidValue_throwsParseException() {
        // Invalid letter
        assertThrows(ParseException.class, () -> ParserUtil.parsePersonSortField("x"));

        // Multiple letters
        assertThrows(ParseException.class, () -> ParserUtil.parsePersonSortField("xyz"));

        // Whitespace
        assertThrows(ParseException.class, () -> ParserUtil.parsePersonSortField(WHITESPACE));
    }

    @Test
    public void parsePersonSortField_validValueWithoutWhitespace_returnsSortField() throws Exception {

        String lowercaseName = "n";
        String uppercaseName = "N";

        String lowercaseGender = "g";
        String uppercaseGender = "G";

        String lowercaseDob = "d";
        String uppercaseDob = "D";

        // Lower case name
        PersonSortField sortFieldLowercaseName = PersonSortField.createSortField(lowercaseName);
        assertEquals(sortFieldLowercaseName, ParserUtil.parsePersonSortField(lowercaseName));

        // Upper case name
        PersonSortField sortFieldUppercaseName = PersonSortField.createSortField(uppercaseName);
        assertEquals(sortFieldUppercaseName, ParserUtil.parsePersonSortField(uppercaseName));

        // Lower case gender
        PersonSortField sortFieldLowercaseGender = PersonSortField.createSortField(lowercaseGender);
        assertEquals(sortFieldLowercaseGender, ParserUtil.parsePersonSortField(lowercaseGender));

        // Upper case gender
        PersonSortField sortFieldUppercaseGender = PersonSortField.createSortField(uppercaseGender);
        assertEquals(sortFieldUppercaseGender, ParserUtil.parsePersonSortField(uppercaseGender));

        // Lower case DOB
        PersonSortField sortFieldLowercaseDob = PersonSortField.createSortField(lowercaseDob);
        assertEquals(sortFieldLowercaseDob, ParserUtil.parsePersonSortField(lowercaseDob));

        // Upper case DOB
        PersonSortField sortFieldUppercaseDob = PersonSortField.createSortField(uppercaseDob);
        assertEquals(sortFieldUppercaseDob, ParserUtil.parsePersonSortField(uppercaseDob));
    }

    @Test
    public void parsePersonSortField_validValueWithWhitespace_returnsSortField() throws Exception {
        String lowercaseName = "n";
        String lowercaseNameWithWhitespace = WHITESPACE + lowercaseName + WHITESPACE;

        PersonSortField sortFieldLowercaseName = PersonSortField.createSortField(lowercaseName);
        assertEquals(sortFieldLowercaseName, ParserUtil.parsePersonSortField(lowercaseNameWithWhitespace));
    }


    //=========== parseEventSortField() Tests =================================================================

    @Test
    public void parseEventSortField_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventSortField((String) null));
    }

    @Test
    public void parseEventSortField_invalidValue_throwsParseException() {
        // Invalid letter
        assertThrows(ParseException.class, () -> ParserUtil.parseEventSortField("x"));

        // Multiple letters
        assertThrows(ParseException.class, () -> ParserUtil.parseEventSortField("xyz"));

        // Whitespace
        assertThrows(ParseException.class, () -> ParserUtil.parseEventSortField(WHITESPACE));
    }

    @Test
    public void parseEventSortField_validValueWithoutWhitespace_returnsSortField() throws Exception {

        String lowercaseEventTitle = "e";
        String uppercaseEventTitle = "E";

        String lowercaseDate = "d";
        String uppercaseDate = "D";

        // Lower case event title
        EventSortField sortFieldLowercaseEventTitle = EventSortField.createSortField(lowercaseEventTitle);
        assertEquals(sortFieldLowercaseEventTitle, ParserUtil.parseEventSortField(lowercaseEventTitle));

        // Upper case event title
        EventSortField sortFieldUppercaseEventTitle = EventSortField.createSortField(uppercaseEventTitle);
        assertEquals(sortFieldUppercaseEventTitle, ParserUtil.parseEventSortField(uppercaseEventTitle));

        // Lower case date
        EventSortField sortFieldLowercaseDate = EventSortField.createSortField(lowercaseDate);
        assertEquals(sortFieldLowercaseDate, ParserUtil.parseEventSortField(lowercaseDate));

        // Upper case date
        EventSortField sortFieldUppercaseDate = EventSortField.createSortField(uppercaseDate);
        assertEquals(sortFieldUppercaseDate, ParserUtil.parseEventSortField(uppercaseDate));
    }

    @Test
    public void parseEventSortField_validValueWithWhitespace_returnsSortField() throws Exception {
        String lowercaseEventTitle = "e";
        String lowercaseEventTitleWithWhitespace = WHITESPACE + lowercaseEventTitle + WHITESPACE;

        EventSortField sortFieldLowercaseEventTitle = EventSortField.createSortField(lowercaseEventTitle);
        assertEquals(sortFieldLowercaseEventTitle, ParserUtil.parseEventSortField(lowercaseEventTitleWithWhitespace));
    }


    //=========== parseName() Tests ===========================================================================

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }


    //=========== parsePhone() Tests ==========================================================================

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }


    //=========== parseAddress() Tests ========================================================================

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }


    //=========== parseEmail() Tests ==========================================================================

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }


    //=========== parseGender() Tests =========================================================================

    @Test
    public void parseGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGender((String) null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    //=========== parseDate() Tests =========================================================================

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null, Boolean.FALSE));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DOB, Boolean.FALSE));
    }

    @Test
    public void parseDate_futureDateNotAllowed_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(FUTURE_DOB, Boolean.FALSE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedDate = new Date(VALID_DOB);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DOB, Boolean.FALSE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DOB + WHITESPACE;
        Date expectedDob = new Date(VALID_DOB);
        assertEquals(expectedDob, ParserUtil.parseDate(dateWithWhitespace, Boolean.FALSE));
    }

    @Test
    public void parseDate_futureDateAllowed_returnsDate() throws Exception {
        Date expectedDob = new Date(FUTURE_DOB);
        assertEquals(expectedDob, ParserUtil.parseDate(FUTURE_DOB, Boolean.TRUE));
    }
}
