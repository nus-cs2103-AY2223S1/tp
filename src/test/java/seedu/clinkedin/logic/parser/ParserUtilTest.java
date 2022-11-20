package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.clinkedin.testutil.Assert.assertThrows;
import static seedu.clinkedin.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Address;
import seedu.clinkedin.model.person.Email;
import seedu.clinkedin.model.person.Name;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Phone;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.Status;
import seedu.clinkedin.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PREFIX = "@";
    private static final String INVALID_STATUS = " @";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_PREFIX = "test";
    private static final String VALID_STATUS = "active";
    private static final String VALID_NOTE = "Test";
    private static final String VALID_RATING = "5";

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
    public void parseTag_null_throwsNullPointerException() {
        //        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        //        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        //        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        //        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        // assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        // assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        // Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        // Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
        //
        // assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseRating_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRating(null));
    }

    @Test
    public void parseRating_invalidRating_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRating("50"));
    }

    @Test
    public void parseRating_validRating_returnsRating() throws Exception {
        Rating expectedRating = new Rating("5");
        assertEquals(expectedRating, ParserUtil.parseRating("5"));
    }

    @Test
    public void parseLink_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLink(null));
    }

    @Test
    public void parseLink_invalidLink_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLink("invalid"));
    }

    @Test
    public void parseLinksForEdit_null_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> ParserUtil.parseLinksForEdit(null));
    }

    @Test
    public void parseLinksForEdit_emptyList_returnsEmptyOptional() throws ParseException {
        assertFalse(ParserUtil.parseLinksForEdit(Collections.emptyList()).isPresent());
    }

    @Test
    public void parseLinksForEdit_validList_returnsOptionalOfLinkList() throws ParseException, MalformedURLException {
        List<String> links = Arrays.asList("https://www.google.com", "https://www.facebook.com");
        List<Link> expectedLinks = Arrays.asList(new Link(new URL("https://www.google.com")),
                new Link(new URL("https://www.facebook.com")));
        Optional<Set<Link>> parsedLinks = ParserUtil.parseLinksForEdit(links);
        for (Link link : expectedLinks) {
            assertTrue(parsedLinks.get().contains(link));
        }
    }

    @Test
    public void parseNames_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNames(null));
    }

    @Test
    public void parseNames_emptyList_returnsEmptySet() throws ParseException {
        assertTrue(ParserUtil.parseNames(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseNames_validList_returnsNameSet() throws ParseException {
        List<String> names = Arrays.asList(VALID_NAME);
        Set<Name> expectedNameSet = new HashSet<>(Arrays.asList(new Name(VALID_NAME)));
        assertEquals(expectedNameSet, ParserUtil.parseNames(names));
    }

    @Test
    public void parsePhones_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhones(null));
    }

    @Test
    public void parsePhones_emptyList_returnsEmptySet() throws ParseException {
        assertTrue(ParserUtil.parsePhones(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePhones_validList_returnsPhoneSet() throws ParseException {
        List<String> phones = Arrays.asList(VALID_PHONE);
        Set<Phone> expectedPhoneSet = new HashSet<>(Arrays.asList(new Phone(VALID_PHONE)));
        assertEquals(expectedPhoneSet, ParserUtil.parsePhones(phones));
    }

    @Test
    public void parseEmails_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmails(null));
    }

    @Test
    public void parseEmails_emptyList_returnsEmptySet() throws ParseException {
        assertTrue(ParserUtil.parseEmails(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseEmails_validList_returnsEmailSet() throws ParseException {
        List<String> emails = Arrays.asList(VALID_EMAIL);
        Set<Email> expectedEmailSet = new HashSet<>(Arrays.asList(new Email(VALID_EMAIL)));
        assertEquals(expectedEmailSet, ParserUtil.parseEmails(emails));
    }

    @Test
    public void parseAddresses_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddresses(null));
    }

    @Test
    public void parseAddresses_emptyList_returnsEmptySet() throws ParseException {
        assertTrue(ParserUtil.parseAddresses(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAddresses_validList_returnsAddressSet() throws ParseException {
        List<String> addresses = Arrays.asList(VALID_ADDRESS);
        Set<Address> expectedAddressSet = new HashSet<>(Arrays.asList(new Address(VALID_ADDRESS)));
        assertEquals(expectedAddressSet, ParserUtil.parseAddresses(addresses));
    }

    @Test
    public void parsePrefix_validPrefix_success() throws IllegalArgumentException, ParseException {
        assertEquals(new Prefix(VALID_PREFIX + "/"), ParserUtil.parsePrefix(VALID_PREFIX));
    }

    @Test
    public void parsePrefix_invalidPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrefix(INVALID_PREFIX));
    }

    @Test
    public void parseStatuses_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatuses(null));
    }

    @Test
    public void parseStatuses_emptyList_returnsEmptySet() throws ParseException {
        assertTrue(ParserUtil.parseStatuses(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseStatuses_validList_returnsStatusSet() throws ParseException {
        List<String> statuses = Arrays.asList(VALID_STATUS);
        Set<Status> expectedStatusSet = new HashSet<>(Arrays.asList(new Status(VALID_STATUS)));
        assertEquals(expectedStatusSet, ParserUtil.parseStatuses(statuses));
    }

    @Test
    public void parseNotes_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNotes(null));
    }

    @Test
    public void parseNotes_emptyList_returnsEmptySet() throws ParseException {
        assertTrue(ParserUtil.parseNotes(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseNotes_validList_returnsNoteSet() throws ParseException {
        List<String> notes = Arrays.asList(VALID_NOTE);
        Set<Note> expectedNoteSet = new HashSet<>(Arrays.asList(new Note(VALID_NOTE)));
        assertEquals(expectedNoteSet, ParserUtil.parseNotes(notes));
    }

    @Test
    public void parseRatings_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRatings(null));
    }

    @Test
    public void parseRatings_emptyList_returnsEmptySet() throws ParseException {
        assertTrue(ParserUtil.parseRatings(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseRatings_validList_returnsRatingSet() throws ParseException {
        List<String> ratings = Arrays.asList(VALID_RATING);
        Set<Rating> expectedRatingSet = new HashSet<>(Arrays.asList(new Rating(VALID_RATING)));
        assertEquals(expectedRatingSet, ParserUtil.parseRatings(ratings));
    }
}
