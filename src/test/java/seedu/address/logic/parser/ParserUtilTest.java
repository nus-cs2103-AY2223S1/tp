package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.link.Link;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_LINK = "googlecom";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_LINK_1 = "https://nus-cs2103-ay2223s1.github.io/website/";
    private static final String VALID_LINK_2 = "https://nusmods.com";
    private static final String VALID_LINK_3 = "google.com";
    private static final String VALID_PHONE = "12345678";

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

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

    @Test
    public void parseLink_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLink(null));
    }

    @Test
    public void parseLink_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLink(INVALID_LINK));
    }

    @Test
    public void parseLink_validValueWithoutHttps_returnsLink() throws Exception {
        Link expectedLink = new Link(VALID_LINK_3);
        assertEquals(expectedLink, ParserUtil.parseLink(VALID_LINK_3));
    }

    @Test
    public void parseLink_validValueWithoutWhitespace_returnsLink() throws Exception {
        Link expectedLink = new Link(VALID_LINK_1);
        assertEquals(expectedLink, ParserUtil.parseLink(VALID_LINK_1));
    }

    @Test
    public void parseLink_validValueWithWhitespace_returnsTrimmedLink() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_LINK_1 + WHITESPACE;
        Link expectedLink = new Link(VALID_LINK_1);
        assertEquals(expectedLink, ParserUtil.parseLink(tagWithWhitespace));
    }

    @Test
    public void parseLinks_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLinks(null));
    }

    @Test
    public void parseLinks_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinks(Arrays.asList(VALID_LINK_1, INVALID_LINK)));
    }

    @Test
    public void parseLinks_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseLinks(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseLink_collectionWithValidLinks_returnsLinkSet() throws Exception {
        Set<Link> actualLinkSet = ParserUtil.parseLinks(Arrays.asList(VALID_LINK_1, VALID_LINK_2));
        Set<Link> expectedLinkSet = new HashSet<Link>(Arrays.asList(new Link(VALID_LINK_1), new Link(VALID_LINK_2)));

        assertEquals(expectedLinkSet, actualLinkSet);
    }
}
