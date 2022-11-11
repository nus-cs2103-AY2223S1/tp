package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_URL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_3;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.link.Link;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_PHONE = "12345678";

    private static final List<String> INVALID_ALIASES_UNIQUE =
            Arrays.asList(INVALID_MODULE_LINK_ALIAS, INVALID_MODULE_LINK_ALIAS_2);
    private static final List<String> VALID_ALIASES_DUPLICATE =
            Arrays.asList(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_ALIAS);
    private static final List<String> VALID_ALIASES_UNIQUE =
            Arrays.asList(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_ALIAS_2);
    private static final List<String> VALID_URLS_UNIQUE =
            Arrays.asList(VALID_MODULE_LINK_URL, VALID_MODULE_LINK_URL_2);
    private static final List<String> VALID_URLS_DUPLICATE =
            Arrays.asList(VALID_MODULE_LINK_URL_2, VALID_MODULE_LINK_URL_2);
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
    public void parseLinkAlias_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLinkAlias(null));
    }

    @Test
    public void parseLinkAlias_validValue() throws Exception {
        String expectedLinkAlias = VALID_MODULE_LINK_ALIAS;
        assertEquals(expectedLinkAlias, ParserUtil.parseLinkAlias(VALID_MODULE_LINK_ALIAS));
    }

    @Test
    public void parseLinkAlias_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinkAlias(INVALID_MODULE_LINK_ALIAS));
    }

    @Test
    public void parseLinkAliases_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLinkAliases(null));
    }

    @Test
    public void parseLinkAliases_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinkAliases(INVALID_ALIASES_UNIQUE));
    }

    @Test
    public void parseLinkAliases_validValue() throws ParseException {
        assertTrue(ParserUtil.parseLinkAliases(VALID_ALIASES_UNIQUE).equals(VALID_ALIASES_UNIQUE));
    }

    @Test
    public void parseLinkUrl_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLinkUrl(null));
    }

    @Test
    public void parseLinkUrl_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinkUrl(INVALID_MODULE_LINK_URL));
    }

    @Test
    public void parseLinkUrl_validValueWithoutHttps_returnsLink() throws Exception {
        String expectedLinkUrl = VALID_MODULE_LINK_URL_3;
        assertEquals(expectedLinkUrl, ParserUtil.parseLinkUrl(VALID_MODULE_LINK_URL_3));
    }

    @Test
    public void parseLinkUrl_validValueWithoutWhitespace_returnsLink() throws Exception {
        String expectedLinkUrl = VALID_MODULE_LINK_URL_3;
        assertEquals(expectedLinkUrl, ParserUtil.parseLinkUrl(VALID_MODULE_LINK_URL_3));
    }

    @Test
    public void parseLinkUrl_validValueWithWhitespace_returnsTrimmedLink() throws Exception {
        String linkUrlWithWhitespace = WHITESPACE + VALID_MODULE_LINK_URL_3 + WHITESPACE;
        String expectedLinkUrl = VALID_MODULE_LINK_URL_3;
        assertEquals(expectedLinkUrl, ParserUtil.parseLinkUrl(linkUrlWithWhitespace));
    }

    @Test
    public void parseLinks_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLinks(null, null));
    }

    @Test
    public void parseLinks_collectionWithInvalidLinkAlias_throwsParseException() {
        List<String> listOfAliasesWithOneInvalidAlias = new ArrayList<>(VALID_ALIASES_UNIQUE);
        listOfAliasesWithOneInvalidAlias.add(INVALID_MODULE_LINK_ALIAS);
        assertThrows(ParseException.class, () -> ParserUtil.parseLinks(
                listOfAliasesWithOneInvalidAlias, VALID_URLS_UNIQUE));
    }

    @Test
    public void parseLinks_collectionWithInvalidLinkUrls_throwsParseException() {
        List<String> listOfUrlsWithOneInvalidUrl = new ArrayList<>(VALID_URLS_UNIQUE);
        listOfUrlsWithOneInvalidUrl.add(INVALID_MODULE_LINK_URL_2);
        assertThrows(ParseException.class, () -> ParserUtil.parseLinks(
                VALID_ALIASES_UNIQUE, listOfUrlsWithOneInvalidUrl));
    }

    @Test
    public void parseLinks_collectionWithDuplicateLinkAlias_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinks(
                VALID_ALIASES_DUPLICATE, VALID_URLS_UNIQUE));
    }

    @Test
    public void parseLinks_collectionWithDuplicateLinkUrls_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinks(VALID_ALIASES_UNIQUE, VALID_URLS_DUPLICATE));
    }

    @Test
    public void parseLinks_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseLinks(Collections.emptyList(), Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseLink_collectionWithValidLinks_returnsLinkSet() throws Exception {
        Set<Link> actualLinkSet = ParserUtil.parseLinks(VALID_ALIASES_UNIQUE, VALID_URLS_UNIQUE);
        List<Link> validListOfLinks = Arrays.asList(new Link(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_URL),
                new Link(VALID_MODULE_LINK_ALIAS_2, VALID_MODULE_LINK_URL_2));
        Set<Link> expectedLinkSet = new HashSet<Link>(validListOfLinks);
        assertEquals(expectedLinkSet, actualLinkSet);
    }

    @Test
    public void isUniqueList_validValue() throws Exception {
        assertEquals(true, ParserUtil.isUniqueList(VALID_ALIASES_UNIQUE));
    }

    @Test
    public void isUniqueList_invalidValue_throwsParseException() {
        assertEquals(false, ParserUtil.isUniqueList(
                Arrays.asList(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_ALIAS)));
    }

    @Test
    public void validateAddLinkCommandPairSize_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.validateAddLinkCommandPairSize(
                VALID_ALIASES_UNIQUE, Arrays.asList(VALID_MODULE_LINK_URL_2)));
    }
}
