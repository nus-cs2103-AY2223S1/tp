package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

    //---------------- Tests for containsSubstringIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsSubstringIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                StringUtil.containsSubstringIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsSubstringIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", () ->
                StringUtil.containsSubstringIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsSubstringIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", () ->
                StringUtil.containsSubstringIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsSubstringIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                StringUtil.containsSubstringIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *   - word that is a substring
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
     *   - middle substring with symbols in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsSubstringIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsSubstringIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsSubstringIgnoreCase("    ", "123"));

        // Matches a partial word
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger
        assertFalse(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger

        // Matches word or partial in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc", "BB")); // First substring (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bBb ccc@1", "@1")); // Last substring (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsSubstringIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsSubstringIgnoreCase("AAA bBb ccc  bbb", "bbB"));
        assertTrue(StringUtil.containsSubstringIgnoreCase("AAA bBb ccc  bbb", "Bb"));
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

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void trimAndReplaceMultipleSpaces() {
        assertEquals(StringUtil.trimAndReplaceMultipleSpaces(null), null);

        assertEquals(StringUtil.trimAndReplaceMultipleSpaces("test "), "test"); // No spaces

        assertEquals(StringUtil.trimAndReplaceMultipleSpaces("test "),
                "test"); // 1 trailing space
        assertEquals(StringUtil.trimAndReplaceMultipleSpaces(" test "),
                "test"); // 1 Leading and trailing space
        assertEquals(StringUtil.trimAndReplaceMultipleSpaces("   test     "),
                "test"); // Multiple Leading and trailing space

        assertEquals(StringUtil.trimAndReplaceMultipleSpaces("test 123"),
                "test 123"); // 1 space in between
        assertEquals(StringUtil.trimAndReplaceMultipleSpaces("test      123"),
                "test 123"); // Multiple spaces in between

        assertEquals(StringUtil.trimAndReplaceMultipleSpaces("   test 123   "),
                "test 123"); // 1 space in between and multiple leading and trailing spaces
        assertEquals(StringUtil.trimAndReplaceMultipleSpaces(" test      123 "),
                "test 123"); // Multiple spaces in between and 1 leading and trailing space
        assertEquals(StringUtil.trimAndReplaceMultipleSpaces("    test      123    "),
                "test 123"); // Multiple spaces in between and multiple leading and trailing spaces
    }

    //---------------- Tests for splitBySlash --------------------------------------

    @Test
    public void splitBySlash() {
        assertThrows(NullPointerException.class, () -> StringUtil.splitBySlash(null)); // null value

        String[] expectedStrArr = {"hehehehe", "hehehehe"};
        String[] secondExpectedStrArr = {"a", "b", "c", "d"};
        String[] thirdExpectedStrArr = {"hey ", " whatsup"};

        assertEquals(StringUtil.splitBySlash("hehehehe/hehehehe")[0], expectedStrArr[0]); // 2 str
        assertEquals(StringUtil.splitBySlash("hehehehe/hehehehe")[1], expectedStrArr[1]); // 2 str

        assertEquals(StringUtil.splitBySlash("a/b/c/d")[0], secondExpectedStrArr[0]); // >=3 str
        assertEquals(StringUtil.splitBySlash("a/b/c/d")[1], secondExpectedStrArr[1]); // >=3 str
        assertEquals(StringUtil.splitBySlash("a/b/c/d")[2], secondExpectedStrArr[2]); // >=3 str
        assertEquals(StringUtil.splitBySlash("a/b/c/d")[3], secondExpectedStrArr[3]); // >=3 str

        assertEquals(StringUtil.splitBySlash("hey / whatsup")[0],
                thirdExpectedStrArr[0]); // strings with whitespace
        assertEquals(StringUtil.splitBySlash("hey / whatsup")[1],
                thirdExpectedStrArr[1]); // strings with whitespace
    }

    //---------------- Tests for convertToDoubleArray --------------------------------------

    @Test
    public void convertToDoubleArray() {
        assertThrows(NullPointerException.class, () -> StringUtil.convertToDoubleArray(null));

        String[] strArr = {"4.2", "3.2"};
        String[] secondStrArr = {"4.19", "3.199"};
        double[] expectedArr = {4.2, 3.2};
        double[] expectedSecondArr = {4.19, 3.199};

        assertEquals(StringUtil.convertToDoubleArray(strArr)[0], expectedArr[0]);
        assertEquals(StringUtil.convertToDoubleArray(secondStrArr)[0], expectedSecondArr[0]);
    }
}
