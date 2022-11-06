package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_MODULE_CODE = "CS2103T%";
    private static final String INVALID_LECTURE_DETAILS = " ";
    private static final String INVALID_TUTORIAL_DETAILS = " ";
    private static final String INVALID_ZOOM_LINK = "zoom.com";
    private static final String INVALID_ASSIGNMENT_DETAILS = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_MODULE_CODE = "CS2103T";
    private static final String VALID_LECTURE_DETAILS = "Every friday";
    private static final String VALID_TUTORIAL_DETAILS = "Every wednesday";
    private static final String VALID_ZOOM_LINK = "https://nus-sg.zoom.us/CS2103T";
    private static final String VALID_ASSIGNMENT_DETAILS_1 = "hard";
    private static final String VALID_ASSIGNMENT_DETAILS_2 = "normal";


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

        // No whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseIndex("  1  "));
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
    public void parseModuleCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCode(INVALID_MODULE_CODE));
    }

    @Test
    public void parseModuleCode_validValueWithoutWhitespace_returnsModuleCode() throws Exception {
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MODULE_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseModuleCode(VALID_MODULE_CODE));
    }

    @Test
    public void parseModuleCode_validValueWithWhitespace_returnsTrimmedModuleCode() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_MODULE_CODE + WHITESPACE;
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MODULE_CODE);
        assertEquals(expectedModuleCode, ParserUtil.parseModuleCode(nameWithWhitespace));
    }

    @Test
    public void parseLectureDetails_validValueWithoutWhitespace_returnsLectureDetails() throws Exception {
        LectureDetails expectedLectureDetails = new LectureDetails(VALID_LECTURE_DETAILS);
        assertEquals(expectedLectureDetails, ParserUtil.parseLectureDetails(VALID_LECTURE_DETAILS));
    }

    @Test
    public void parseLectureDetails_validValueWithWhitespace_returnsTrimmedLectureDetails() throws Exception {
        String lectureDetailsWithWhitespace = WHITESPACE + VALID_LECTURE_DETAILS + WHITESPACE;
        LectureDetails expectedLectureDetails = new LectureDetails(VALID_LECTURE_DETAILS);
        assertEquals(expectedLectureDetails, ParserUtil.parseLectureDetails(lectureDetailsWithWhitespace));
    }

    @Test
    public void parseTutorialDetails_validValueWithoutWhitespace_returnsTutorialDetails() throws Exception {
        TutorialDetails expectedTutorialDetails = new TutorialDetails(VALID_TUTORIAL_DETAILS);
        assertEquals(expectedTutorialDetails, ParserUtil.parseTutorialDetails(VALID_TUTORIAL_DETAILS));
    }

    @Test
    public void parseTutorialDetails_validValueWithWhitespace_returnsTrimmedTutorialDetails() throws Exception {
        String tutorialDetailsWithWhitespace = WHITESPACE + VALID_TUTORIAL_DETAILS + WHITESPACE;
        TutorialDetails expectedTutorialDetails = new TutorialDetails(VALID_TUTORIAL_DETAILS);
        assertEquals(expectedTutorialDetails, ParserUtil.parseTutorialDetails(tutorialDetailsWithWhitespace));
    }

    @Test
    public void parseZoomLink_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseZoomLink(INVALID_ZOOM_LINK));
    }

    @Test
    public void parseZoomLink_validValueWithoutWhitespace_returnsZoomLink() throws Exception {
        ZoomLink expectedZoomLink = new ZoomLink(VALID_ZOOM_LINK);
        assertEquals(expectedZoomLink, ParserUtil.parseZoomLink(VALID_ZOOM_LINK));
    }

    @Test
    public void parseZoomLink_validValueWithWhitespace_returnsTrimmedZoomLink() throws Exception {
        String zoomLinkWithWhitespace = WHITESPACE + VALID_ZOOM_LINK + WHITESPACE;
        ZoomLink expectedZoomLink = new ZoomLink(VALID_ZOOM_LINK);
        assertEquals(expectedZoomLink, ParserUtil.parseZoomLink(zoomLinkWithWhitespace));
    }

    @Test
    public void parseAssignmentDetail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAssignmentDetail(null));
    }

    @Test
    public void parseAssignmentDetail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAssignmentDetail(INVALID_ASSIGNMENT_DETAILS));
    }

    @Test
    public void parseAssignmentDetail_validValueWithoutWhitespace_returnsTag() throws Exception {
        AssignmentDetails expectedAssignmentDetail = new AssignmentDetails(VALID_ASSIGNMENT_DETAILS_1);
        assertEquals(expectedAssignmentDetail, ParserUtil.parseAssignmentDetail(VALID_ASSIGNMENT_DETAILS_1));
    }

    @Test
    public void parseAssignmentDetail_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String assignmentDetailWithWhitespace = WHITESPACE + VALID_ASSIGNMENT_DETAILS_1 + WHITESPACE;
        AssignmentDetails expectedAssignmentDetail = new AssignmentDetails(VALID_ASSIGNMENT_DETAILS_1);
        assertEquals(expectedAssignmentDetail, ParserUtil.parseAssignmentDetail(assignmentDetailWithWhitespace));
    }

    @Test
    public void parseAssignmentDetails_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAssignmentDetails(null));
    }

    @Test
    public void parseAssignmentDetails_collectionWithInvalidAssignmentDetails_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAssignmentDetails(
            Arrays.asList(VALID_ASSIGNMENT_DETAILS_1, INVALID_ASSIGNMENT_DETAILS)));
    }

    @Test
    public void parseAssignmentDetails_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseAssignmentDetails(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAssignmentDetails_collectionWithValidAssignmentDetails_returnsTagSet() throws Exception {
        Set<AssignmentDetails> actualTagSet = ParserUtil.parseAssignmentDetails(
            Arrays.asList(VALID_ASSIGNMENT_DETAILS_1, VALID_ASSIGNMENT_DETAILS_2));
        Set<AssignmentDetails> expectedTagSet = new HashSet<AssignmentDetails>(
            Arrays.asList(new AssignmentDetails(VALID_ASSIGNMENT_DETAILS_1),
                new AssignmentDetails(VALID_ASSIGNMENT_DETAILS_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
