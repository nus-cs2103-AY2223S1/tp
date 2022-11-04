package seedu.taassist.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.commons.util.StringUtil.caseInsensitiveEquals;
import static seedu.taassist.testutil.Assert.assertThrows;

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
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
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

    //---------------- Tests for getDetails --------------------------------------

    @Test
    public void caseInsensitiveEquals_sameString_equals() {
        String testString = "testString";
        String testString2 = "TestString";
        assertTrue(caseInsensitiveEquals(testString, testString));
        assertTrue(caseInsensitiveEquals(testString, testString2));
    }

    @Test
    public void caseInsensitiveEquals_differentStrings_notEquals() {
        String testString = "testString";
        String differentTestString = "differenttt";
        assertFalse(caseInsensitiveEquals(testString, differentTestString));
    }

    //---------------- Tests for capitalise --------------------------------------

    @Test
    public void capitalise_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.capitalise(null));
    }

    @Test
    public void capitalise_emptyString_returnsEmptyString() {
        String inputString = "";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals(outputString, inputString);
    }

    @Test
    public void capitalise_whiteSpace_returnsEmptyString() {
        String inputString = "  ";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals("", outputString);
    }

    @Test
    public void capitalise_singleLetter_returnsUpperCasedLetter() {
        String inputString = "a";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals("A", outputString);
    }

    @Test
    public void capitalise_singleSymbol_returnsSymbol() {
        String inputString = "_";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals(inputString, outputString);
    }

    @Test
    public void capitalise_singleLowerCasedWord_returnsCapitalisedWord() {
        String inputString = "foo";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals("Foo", outputString);
    }

    @Test
    public void capitalise_singleUpperCasedWord_returnsCapitalisedWord() {
        String inputString = "FOO";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals("Foo", outputString);
    }

    @Test
    public void capitalise_lowerCasedSentence_returnsCapitalisedString() {
        String inputString = "the quick brown fox jumps over the lazy dog";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals("The Quick Brown Fox Jumps Over The Lazy Dog", outputString);
    }

    @Test
    public void capitalise_upperCasedSentence_returnsCapitalisedString() {
        String inputString = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals("The Quick Brown Fox Jumps Over The Lazy Dog", outputString);
    }

    @Test
    public void capitalise_sentenceWithSymbols_returnsCapitalisedString() {
        String inputString = "@the quick brown_fox jumps over the lazy_dog! wow.";
        String outputString = StringUtil.capitalise(inputString);
        assertEquals("@the Quick Brown_fox Jumps Over The Lazy_dog! Wow.", outputString);
    }
}
