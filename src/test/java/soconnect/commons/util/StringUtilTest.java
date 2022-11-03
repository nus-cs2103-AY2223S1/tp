package soconnect.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static soconnect.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

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
        // Sentence word bigger than query word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb"));
        // Query word bigger than sentence word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbbb"));

        // Matches word in the sentence, different upper/lower case letters
        // First word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc", "Bbb"));
        // Last word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc@1", "CCc@1"));
        // Sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa"));
        // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("Aaa", "aaa"));
        // Leading/trailing spaces
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "  ccc  "));

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }


    //---------------- Tests for containsKeywordsIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty
     * Invalid equivalence partitions for sentence: null
     * The three test cases below test one invalid input at a time.
     */

    @Test
    public void containsKeywordsIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                StringUtil.containsKeywordsIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsKeywordsIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", () ->
                StringUtil.containsKeywordsIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsKeywordsIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                StringUtil.containsKeywordsIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - one word
     *   - multiple words
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
    public void containsKeywordsIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsKeywordsIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsKeywordsIgnoreCase("    ", "123"));

        // Matches a partial word only
        // Sentence word bigger than query word
        assertFalse(StringUtil.containsKeywordsIgnoreCase("aaa bbb ccc", "bb"));
        // Query word bigger than sentence word
        assertFalse(StringUtil.containsKeywordsIgnoreCase("aaa bbb ccc", "bbbb"));

        // Matches word in the sentence, different upper/lower case letters
        // First word (boundary case)
        assertTrue(StringUtil.containsKeywordsIgnoreCase("aaa bBb ccc", "Bbb"));
        // Last word (boundary case)
        assertTrue(StringUtil.containsKeywordsIgnoreCase("aaa bBb ccc@1", "CCc@1"));
        // Sentence has extra spaces
        assertTrue(StringUtil.containsKeywordsIgnoreCase("  AAA   bBb   ccc  ", "aaa"));
        // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsKeywordsIgnoreCase("Aaa", "aaa"));
        // Leading/trailing spaces
        assertTrue(StringUtil.containsKeywordsIgnoreCase("aaa bbb ccc", "  ccc  "));

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsKeywordsIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    @Test
    public void containsKeywordsIgnoreCase_singleWord_match() {
        assertTrue(StringUtil.containsKeywordsIgnoreCase("John Doe Damian", "John"));
    }

    @Test
    public void containsKeywordsIgnoreCase_singleWord_doNotMatch() {
        assertFalse(StringUtil.containsKeywordsIgnoreCase("John Doe Damian", "Rachel"));
    }

    @Test
    public void containsKeywordsIgnoreCase_singleWordDifferentOrder_doNotMatch() {
        assertFalse(StringUtil.containsKeywordsIgnoreCase("John Doe Damian", "Jhon"));
    }

    @Test
    public void containsKeywordsIgnoreCase_multipleWordsDifferentCase_match() {
        assertTrue(StringUtil.containsKeywordsIgnoreCase("John Doe Damian", "john doe"));
    }

    @Test
    public void containsKeywordsIgnoreCase_multipleWords_doNotMatch() {
        assertFalse(StringUtil.containsKeywordsIgnoreCase("John Doe Damian", "John DD"));
    }

    @Test
    public void containsKeywordsIgnoreCase_multipleWordsDifferentOrder_match() {
        assertTrue(StringUtil.containsKeywordsIgnoreCase("John Doe Damian", "Damian doe"));
    }


    //---------------- Tests for containsSomeKeywordsIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty
     * Invalid equivalence partitions for sentence: null
     * The three test cases below test one invalid input at a time.
     */

    @Test
    public void containsSomeKeywordsIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                StringUtil.containsSomeKeywordsIgnoreCase("typical sentence", null, false));
    }

    @Test
    public void containsSomeKeywordsIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                StringUtil.containsSomeKeywordsIgnoreCase(null, "abc", true));
    }

    /*
     * Valid equivalence partitions for word:
     *   - one word
     *   - multiple words
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
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * Possible scenarios returning false:
     *   - less than 3/4 of query words matches part of a sentence word
     *   - sentence word matches none of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsSomeKeywordsIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsSomeKeywordsIgnoreCase("", "abc", false)); // Boundary case
        assertFalse(StringUtil.containsSomeKeywordsIgnoreCase("    ", "123", false));

        // Matches a partial word only
        // Sentence word bigger than query word
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("aaa bbb ccc", "bb", false));
        // Query word bigger than sentence word
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("aaa bbb ccc", "bbbb", false));

        // Matches word in the sentence, different upper/lower case letters
        // First word (boundary case)
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("aaa bBb ccc", "Bbb", false));
        // Last word (boundary case)
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("aaa bBb ccc@1", "CCc@1", false));
        // Sentence has extra spaces
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("  AAA   bBb   ccc  ", "aaa", false));
        // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("Aaa", "aaa", false));
        // Leading/trailing spaces
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("aaa bbb ccc", "  ccc  ", false));

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("AAA bBb ccc  bbb", "bbB", false));
    }

    @Test
    public void containsSomeKeywordsIgnoreCase_singleWord_match() {
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("John Doe Damian", "John", false));
    }

    @Test
    public void containsSomeKeywordsIgnoreCase_singleWord_doNotMatch() {
        assertFalse(StringUtil.containsSomeKeywordsIgnoreCase("John Doe Damian", "Rachel", false));
    }

    @Test
    public void containsSomeKeywordsIgnoreCase_singleWordDifferentOrder_match() {
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("John Doe Damian", "Jhon", false));
    }

    @Test
    public void containsSomeKeywordsIgnoreCase_multipleWordsDifferentCase_match() {
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("John Doe Damian", "john doe", false));
    }

    @Test
    public void containsSomeKeywordsIgnoreCase_multipleWordsPartiallySame_match() {
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("John Doe Damian", "John DD", true));
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("John Doe Damian", "Johm Damyan", true));
    }

    @Test
    public void containsSomeKeywordsIgnoreCase_multipleWordsDifferentOrder_match() {
        assertTrue(StringUtil.containsSomeKeywordsIgnoreCase("John Doe Damian", "Damian doe", false));
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

}
