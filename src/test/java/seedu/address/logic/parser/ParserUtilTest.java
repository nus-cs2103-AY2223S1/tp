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
    private static final String INVALID_NAME = "A@libaba";
    private static final String INVALID_LINK = "+651234";
    private static final String INVALID_APPLIED_DATE = " ";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_TAG = "#Frontend";
    private static final String INVALID_APPLICATION_STATUS = "waiting";

    private static final String VALID_COMPANY = "Alibaba";
    private static final String VALID_LINK = "https://careers.alibaba.com/positionDetail";
    private static final String VALID_APPLIED_DATE = "01/10/2022";
    private static final String VALID_DESCRIPTION = "Software Engineering Intern";
    private static final String VALID_TAG_1 = "Frontend";
    private static final String VALID_TAG_2 = "Backend";
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
    public void parseCompany_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompany((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parseCompany_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_NAME));
    }
    */

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
    public void parseLink_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLink((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parseLink_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLink(INVALID_PHONE));
    }
    */

    @Test
    public void parseLink_validValueWithoutWhitespace_returnsLink() throws Exception {
        Link expectedLink = new Link(VALID_LINK);
        assertEquals(expectedLink, ParserUtil.parseLink(VALID_LINK));
    }

    @Test
    public void parseLink_validValueWithWhitespace_returnsTrimmedLink() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_LINK + WHITESPACE;
        Link expectedLink = new Link(VALID_LINK);
        assertEquals(expectedLink, ParserUtil.parseLink(phoneWithWhitespace));
    }

    @Test
    public void parseAppliedDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppliedDate((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parseAppliedDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppliedDate(INVALID_ADDRESS));
    }
    */

    @Test
    public void parseAppliedDate_validValueWithoutWhitespace_returnsAppliedDate() throws Exception {
        AppliedDate expectedAppliedDate = new AppliedDate(VALID_APPLIED_DATE);
        assertEquals(expectedAppliedDate, ParserUtil.parseAppliedDate(VALID_APPLIED_DATE));
    }

    @Test
    public void parseAppliedDate_validValueWithWhitespace_returnsTrimmedAppliedDate() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_APPLIED_DATE + WHITESPACE;
        AppliedDate expectedAppliedDate = new AppliedDate(VALID_APPLIED_DATE);
        assertEquals(expectedAppliedDate, ParserUtil.parseAppliedDate(addressWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_EMAIL));
    }
    */
    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
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
