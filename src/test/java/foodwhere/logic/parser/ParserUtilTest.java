package foodwhere.logic.parser;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import foodwhere.model.review.Content;
import foodwhere.model.review.Rating;
import foodwhere.model.review.comparator.ReviewsComparatorList;
import foodwhere.model.stall.comparator.StallsComparatorList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.testutil.TypicalIndexes;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_CONTENT = " ";
    private static final String INVALID_RATING = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_STALL_CRITERIA = " ";
    private static final String INVALID_REVIEW_CRITERIA = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_NAMELIST = "Rachel Bob";
    private static final String VALID_NAMELIST_FIRST = "Rachel";
    private static final String VALID_NAMELIST_SECOND = "Bob";
    private static final String VALID_TAGLIST = "Rachel Bob";
    private static final String VALID_TAGLIST_FIRST = "Rachel";
    private static final String VALID_TAGLIST_SECOND = "Bob";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_CONTENT = "Very tasty";
    private static final String VALID_RATING = "3";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_STALL_CRITERIA = "NAME";
    private static final String VALID_REVIEW_CRITERIA = "RATING";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, Messages.MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        Assertions.assertEquals(TypicalIndexes.INDEX_FIRST_STALL, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        Assertions.assertEquals(TypicalIndexes.INDEX_FIRST_STALL, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
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
    public void parseNameList_validValue_returnsNameSet() throws Exception {
        String nameList = VALID_NAMELIST;
        Set<Name> expectedNameList = new HashSet<Name>();
        expectedNameList.add(new Name(VALID_NAMELIST_FIRST));
        expectedNameList.add(new Name(VALID_NAMELIST_SECOND));
        assertEquals(expectedNameList, ParserUtil.parseNameList(nameList));
    }

    @Test
    public void parseTagList_validValue_returnsTagSet() throws Exception {
        String tagList = VALID_TAGLIST;
        Set<Tag> expectedTagList = new HashSet<Tag>();
        expectedTagList.add(new Tag(VALID_TAGLIST_FIRST));
        expectedTagList.add(new Tag(VALID_TAGLIST_SECOND));
        assertEquals(expectedTagList, ParserUtil.parseTagList(tagList));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
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
    public void parseContent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContent(null));
    }

    @Test
    public void parseContent_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContent(INVALID_CONTENT));
    }

    @Test
    public void parseContent_validValueWithoutWhitespace_returnsContent() throws Exception {
        Content expectedContent = new Content(VALID_CONTENT);
        assertEquals(expectedContent, ParserUtil.parseContent(VALID_CONTENT));
    }

    @Test
    public void parseContent_validValueWithWhitespace_returnsTrimmedContent() throws Exception {
        String contentWithWhitespace = WHITESPACE + VALID_CONTENT + WHITESPACE;
        Content expectedContent = new Content(VALID_CONTENT);
        assertEquals(expectedContent, ParserUtil.parseContent(contentWithWhitespace));
    }

    @Test
    public void parseRating_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRating(null));
    }

    @Test
    public void parseRating_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRating(INVALID_RATING));
    }

    @Test
    public void parseRating_validValueWithoutWhitespace_returnsRating() throws Exception {
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(VALID_RATING));
    }

    @Test
    public void parseRating_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String ratingWithWhitespace = WHITESPACE + VALID_RATING + WHITESPACE;
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(ratingWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
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
        assertThrows(ParseException.class, () ->
                ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1),
                new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseStallCriteria_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStallCriteria(null));
    }

    @Test
    public void parseStallCriteria_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStallCriteria(INVALID_STALL_CRITERIA));
        assertThrows(ParseException.class, () -> ParserUtil.parseStallCriteria("123456"));
    }

    @Test
    public void parseStallCriteria_validValueWithoutWhitespace_returnsRating() throws Exception {
        StallsComparatorList expectedCriteria = StallsComparatorList.valueOf(VALID_STALL_CRITERIA);
        assertEquals(expectedCriteria, ParserUtil.parseStallCriteria(VALID_STALL_CRITERIA));
    }

    @Test
    public void parseStallCriteria_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String criteriaWithWhitespace = WHITESPACE + VALID_STALL_CRITERIA + WHITESPACE;
        StallsComparatorList expectedCriteria = StallsComparatorList.valueOf(VALID_STALL_CRITERIA);
        assertEquals(expectedCriteria, ParserUtil.parseStallCriteria(criteriaWithWhitespace));
    }

    @Test
    public void parseReviewCriteria_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseReviewCriteria(null));
    }

    @Test
    public void parseReviewCriteria_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseReviewCriteria(INVALID_REVIEW_CRITERIA));
        assertThrows(ParseException.class, () -> ParserUtil.parseReviewCriteria("12345"));
    }

    @Test
    public void parseReviewCriteria_validValueWithoutWhitespace_returnsRating() throws Exception {
        ReviewsComparatorList expectedCriteria = ReviewsComparatorList.valueOf(VALID_REVIEW_CRITERIA);
        assertEquals(expectedCriteria, ParserUtil.parseReviewCriteria(VALID_REVIEW_CRITERIA));
    }

    @Test
    public void parseReviewCriteria_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String criteriaWithWhitespace = WHITESPACE + VALID_REVIEW_CRITERIA + WHITESPACE;
        ReviewsComparatorList expectedCriteria = ReviewsComparatorList.valueOf(VALID_REVIEW_CRITERIA);
        assertEquals(expectedCriteria, ParserUtil.parseReviewCriteria(criteriaWithWhitespace));
    }
}
