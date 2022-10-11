package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_APPLICATION_STATUS = "waiting";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_APPLICATION_STATUS = "accepted";

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
        assertEquals(INDEX_FIRST_INTERNSHIP, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_INTERNSHIP, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompany((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_NAME));
    }
    */

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Company expectedCompany = new Company(VALID_NAME);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Company expectedCompany = new Company(VALID_NAME);
        assertEquals(expectedCompany, ParserUtil.parseCompany(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLink((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLink(INVALID_PHONE));
    }
    */

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Link expectedLink = new Link(VALID_PHONE);
        assertEquals(expectedLink, ParserUtil.parseLink(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Link expectedLink = new Link(VALID_PHONE);
        assertEquals(expectedLink, ParserUtil.parseLink(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppliedDate((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppliedDate(INVALID_ADDRESS));
    }
    */

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        AppliedDate expectedAppliedDate = new AppliedDate(VALID_ADDRESS);
        assertEquals(expectedAppliedDate, ParserUtil.parseAppliedDate(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        AppliedDate expectedAppliedDate = new AppliedDate(VALID_ADDRESS);
        assertEquals(expectedAppliedDate, ParserUtil.parseAppliedDate(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_EMAIL));
    }
    */
    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Description expectedDescription = new Description(VALID_EMAIL);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Description expectedDescription = new Description(VALID_EMAIL);
        assertEquals(expectedDescription, ParserUtil.parseDescription(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseApplicationStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseApplicationStatus((String) null));
    }

    @Test
    public void parseApplicationStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseApplicationStatus(INVALID_APPLICATION_STATUS));
    }

    @Test
    public void parseApplicationStatus_validValueWithoutWhitespace_returnsApplicationStatus() throws Exception {
        ApplicationStatus expectedApplicationStatus = ApplicationStatus.parse(VALID_APPLICATION_STATUS);
        assertEquals(expectedApplicationStatus, ParserUtil.parseApplicationStatus(VALID_APPLICATION_STATUS));
    }

    @Test
    public void parseApplicationStatus_validValueWithWhitespace_returnsTrimmedApplicationStatus() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_APPLICATION_STATUS + WHITESPACE;
        ApplicationStatus expectedApplicationStatus = ApplicationStatus.parse(VALID_APPLICATION_STATUS);
        assertEquals(expectedApplicationStatus, ParserUtil.parseApplicationStatus(VALID_APPLICATION_STATUS));
    }
}
