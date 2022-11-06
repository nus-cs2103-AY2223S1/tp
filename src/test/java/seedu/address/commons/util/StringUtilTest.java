package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));

        // Matches a partial word only
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

    //---------------- Tests for removeDuplicateWhitespace --------------------------------------
    @Test
    public void removeDuplicateWhitespace_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.removeDuplicateWhitespace(null));
    }

    @Test
    public void removeDuplicateWhitespace_validInputs_correctResult() {
        //dont do anything
        assertEquals("", StringUtil.removeDuplicateWhitespace(""));
        assertEquals("abc", StringUtil.removeDuplicateWhitespace("abc"));
        assertEquals("a b c", StringUtil.removeDuplicateWhitespace("a b c"));


        assertEquals(" ", StringUtil.removeDuplicateWhitespace("    "));
        assertEquals("a b c", StringUtil.removeDuplicateWhitespace("a   b   c"));
        assertEquals(" abc ", StringUtil.removeDuplicateWhitespace("   abc   "));
    }
    //---------------- Tests for removeDuplicateComma--------------------------------------
    @Test
    public void removeDuplicateComma_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.removeDuplicateComma(null));
    }

    @Test
    public void removeDuplicateComma_validInputs_correctResult() {
        //dont do anything
        assertEquals("", StringUtil.removeDuplicateComma(""));
        assertEquals("abc", StringUtil.removeDuplicateComma("abc"));
        assertEquals("a,b,c", StringUtil.removeDuplicateComma("a,b,c"));


        assertEquals(",", StringUtil.removeDuplicateComma(",,,,"));
        assertEquals("a,b,c", StringUtil.removeDuplicateComma("a,,,b,,,c"));
        assertEquals(",abc,", StringUtil.removeDuplicateComma(",,,abc,,,"));
    }
    //---------------- Tests for capitaliseOnlyFirstLetter--------------------------------------
    @Test
    public void capitaliseOnlyFirstLetter_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.capitaliseOnlyFirstLetter(null));
    }

    @Test
    public void capitaliseOnlyFirstLetter_validInputs_correctResult() {
        //dont do anything
        assertEquals("", StringUtil.capitaliseOnlyFirstLetter(""));
        assertEquals("Abc", StringUtil.capitaliseOnlyFirstLetter("Abc"));

        assertEquals("A Better Car", StringUtil.capitaliseOnlyFirstLetter("a better car"));
    }

    //---------------- Tests for removeWhitespaceForLevel--------------------------------------
    @Test
    public void removeWhitespaceForLevel_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.removeWhitespaceForLevel(null));
    }

    @Test
    public void removeWhitespaceForLevel_validInputs_correctResult() {
        //dont do anything
        assertEquals("", StringUtil.removeWhitespaceForLevel(""));
        assertEquals("a b c", StringUtil.removeWhitespaceForLevel("a b c"));
        assertEquals("primary3", StringUtil.removeWhitespaceForLevel("primary3"));
        assertEquals("secondary3", StringUtil.removeWhitespaceForLevel("secondary3"));

        assertEquals("primary3", StringUtil.removeWhitespaceForLevel("primary 3"));
        assertEquals("secondary3", StringUtil.removeWhitespaceForLevel("secondary 3"));
        assertEquals("primary3", StringUtil.removeWhitespaceForLevel("primary    3"));
        assertEquals("secondary3", StringUtil.removeWhitespaceForLevel("secondary    3"));
    }

    //---------------- Tests for convertShortFormLevel--------------------------------------
    @Test
    public void convertShortFormLevel_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.convertShortFormLevel(null));
    }

    @Test
    public void convertShortFormLevel_validInputs_correctResult() {
        //dont do anything
        assertEquals("", StringUtil.convertShortFormLevel(""));
        assertEquals("a b c", StringUtil.convertShortFormLevel("a b c"));
        assertEquals("problem3", StringUtil.convertShortFormLevel("problem3"));
        assertEquals("second3", StringUtil.convertShortFormLevel("second3"));
        assertEquals("primary3", StringUtil.convertShortFormLevel("primary3"));
        assertEquals("secondary3", StringUtil.convertShortFormLevel("secondary3"));

        assertEquals("primary3", StringUtil.convertShortFormLevel("p3"));
        assertEquals("secondary3", StringUtil.convertShortFormLevel("s3"));
        assertEquals("primary3", StringUtil.convertShortFormLevel("pri3"));
        assertEquals("secondary3", StringUtil.convertShortFormLevel("sec3"));

        assertEquals("primary3", StringUtil.convertShortFormLevel("p  3"));
        assertEquals("secondary3", StringUtil.convertShortFormLevel("s  3"));
    }

    //---------------- Tests for formatTime--------------------------------------
    @Test
    public void formatTime_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.formatTime(null));
    }

    @Test
    public void formatTime_unacceptedTimeFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> StringUtil.formatTime(""));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("13:00:20"));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("10"));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("13am"));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("13pm"));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("13:00am"));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("13:00pm"));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("1300am"));
        assertThrows(ParseException.class, () -> StringUtil.formatTime("1300pm"));
    }

    @Test
    public void formatTime_validInputs_correctResult() throws Exception {
        assertEquals("10:00", StringUtil.formatTime("10am"));
        assertEquals("22:00", StringUtil.formatTime("10pm"));
        assertEquals("10:30", StringUtil.formatTime("10:30am"));
        assertEquals("22:30", StringUtil.formatTime("10:30pm"));
        assertEquals("10:30", StringUtil.formatTime("10:30"));
        assertEquals("10:30", StringUtil.formatTime("1030"));
        assertEquals("01:30", StringUtil.formatTime("130"));
        assertEquals("01:30", StringUtil.formatTime("1:30"));

        //formatTime not meant to catch these as its only a format converter
        assertEquals("10:99", StringUtil.formatTime("10:99"));
        assertEquals("99:00", StringUtil.formatTime("99:00"));
    }

}
