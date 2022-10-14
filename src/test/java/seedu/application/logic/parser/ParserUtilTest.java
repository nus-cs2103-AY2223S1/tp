package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;

public class ParserUtilTest {
    private static final String INVALID_COMPANY = "G@@gle";
    private static final String INVALID_CONTACT = "+651234";
    private static final String INVALID_DATE = "01/01/2022";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_POSITION = " ";

    private static final String VALID_COMPANY = "Google";
    private static final String VALID_CONTACT = "123456";
    private static final String VALID_DATE = "2022-01-01";
    private static final String VALID_EMAIL = "google@example.com";
    private static final String VALID_POSITION = "Software Engineer";

    private static final String WHITESPACE = " \t\r\n";

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
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseCompany_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompany((String) null));
    }

    @Test
    public void parseCompany_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithoutWhitespace_returnsCompany() throws Exception {
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithWhitespace_returnsTrimmedCompany() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_COMPANY + WHITESPACE;
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(nameWithWhitespace));
    }

    @Test
    public void parseContact_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContact((String) null));
    }

    @Test
    public void parseContact_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContact(INVALID_CONTACT));
    }

    @Test
    public void parseContact_validValueWithoutWhitespace_returnsContact() throws Exception {
        Contact expectedContact = new Contact(VALID_CONTACT);
        assertEquals(expectedContact, ParserUtil.parseContact(VALID_CONTACT));
    }

    @Test
    public void parseContact_validValueWithWhitespace_returnsTrimmedContact() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_CONTACT + WHITESPACE;
        Contact expectedContact = new Contact(VALID_CONTACT);
        assertEquals(expectedContact, ParserUtil.parseContact(phoneWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
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

    @Test
    public void parsePosition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePosition(null));
    }

    @Test
    public void parsePosition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePosition(INVALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithoutWhitespace_returnsPosition() throws Exception {
        Position expectedPosition = new Position(VALID_POSITION);
        assertEquals(expectedPosition, ParserUtil.parsePosition(VALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithWhitespace_returnsTrimmedPosition() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_POSITION + WHITESPACE;
        Position expectedPosition = new Position(VALID_POSITION);
        assertEquals(expectedPosition, ParserUtil.parsePosition(tagWithWhitespace));
    }
}
