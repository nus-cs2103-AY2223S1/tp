package seedu.guest.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

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

    //---------------- Tests for containsDateIgnoreHyphenIgnoreSpace --------------------------------------

    /*
     * Invalid equivalence partitions for date: null, empty, multiple dates
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsDateIgnoreHyphenIgnoreSpace_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsDateIgnoreHyphenIgnoreSpace(
                "13/03/22", null));
    }

    @Test
    public void containsDateIgnoreHyphenIgnoreSpace_emptyDate_returnsFalse() {
        assertEquals(false, StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22", "  "));
    }

    @Test
    public void containsDateIgnoreHyphenIgnoreSpace_multipleDates_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Date parameter should be a single date", ()
                -> StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22", "13/03/22 14/03/22"));
    }

    @Test
    public void containsDateIgnoreHyphenIgnoreSpace_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsDateIgnoreHyphenIgnoreSpace(
                null, "13/03/22"));
    }

    /*
     * Valid equivalence partitions for date:
     *   - valid date
     *   - date with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one date
     *   - multiple dates
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first date in sentence
     *   - last date in sentence
     *   - middle date in sentence
     *   - matches multiple dates
     *
     * Possible scenarios returning false:
     *   - query date matches part of a sentence date
     *   - sentence date matches part of the query date
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsDateIgnoreHyphenIgnoreSpace_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsDateIgnoreHyphenIgnoreSpace("", "13/03/22")); // Boundary case
        assertFalse(StringUtil.containsDateIgnoreHyphenIgnoreSpace("    ", "13/03/22"));

        // Sentence date bigger than query date
        assertFalse(StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22", "13/03"));
        // Query date bigger than sentence date
        assertFalse(StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22", "13/03/2022"));

        // Matches dates in sentence with multiple dates
        assertTrue(StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22 14/03/22 15/03/22", "13/03/22"));
        assertTrue(StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22 14/03/22 15/03/22", "14/03/22"));
        assertTrue(StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22 14/03/22 15/03/22", "15/03/22"));
        assertTrue(StringUtil.containsDateIgnoreHyphenIgnoreSpace("13/03/22 13/03/22 13/03/22", "13/03/22"));
    }

    //---------------- Tests for containsIsRoomCleanIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for isRoomClean: null, empty, multiple isRoomCleans
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsIsRoomCleanIgnoreCase_nullIsRoomClean_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsIsRoomCleanIgnoreCase("yes", null));
    }

    @Test
    public void containsIsRoomCleanIgnoreCase_emptyIsRoomClean_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "IsRoomClean parameter cannot be empty", ()
                -> StringUtil.containsIsRoomCleanIgnoreCase("yes", "  "));
    }

    @Test
    public void containsIsRoomCleanIgnoreCase_multipleIsRoomCleans_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "IsRoomClean parameter should be a single IsRoomClean", ()
                -> StringUtil.containsIsRoomCleanIgnoreCase("yes", "yes no"));
    }

    @Test
    public void containsIsRoomCleanIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsIsRoomCleanIgnoreCase(null, "yes"));
    }

    /*
     * Valid equivalence partitions for isRoomClean:
     *   - valid isRoomClean
     *   - isRoomClean with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one isRoomClean
     *   - multiple isRoomCleans
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first isRoomClean in sentence
     *   - last isRoomClean in sentence
     *   - middle isRoomClean in sentence
     *   - matches multiple isRoomCleans
     *
     * Possible scenarios returning false:
     *   - query isRoomClean matches part of a sentence isRoomClean
     *   - sentence isRoomClean matches part of the query isRoomClean
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsIsRoomCleanIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsIsRoomCleanIgnoreCase("", "yes")); // Boundary case
        assertFalse(StringUtil.containsIsRoomCleanIgnoreCase("    ", "yes"));

        // Sentence isRoomClean bigger than query isRoomClean
        assertFalse(StringUtil.containsIsRoomCleanIgnoreCase("yes", "ye"));
        // Query isRoomClean bigger than sentence isRoomClean
        assertFalse(StringUtil.containsIsRoomCleanIgnoreCase("yes", "yess"));

        // Matches isRoomClean in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsIsRoomCleanIgnoreCase("yEs no no", "yes")); // First isRoomClean (boundary case)
        assertTrue(StringUtil.containsIsRoomCleanIgnoreCase("yes yes No", "no")); // Last isRoomClean (boundary case)
        assertTrue(StringUtil.containsIsRoomCleanIgnoreCase("  yes   yes   No  ", "yes")); // Sentence has extra spaces
        assertTrue(StringUtil.containsIsRoomCleanIgnoreCase("Yes", "yes")); // Only one isRoomClean in sentence
        assertTrue(StringUtil.containsIsRoomCleanIgnoreCase("yes", "  yes  ")); // Leading/trailing spaces

        // Matches multiple isRoomCleans in sentence
        assertTrue(StringUtil.containsIsRoomCleanIgnoreCase("YES yES nO yeS", "Yes"));
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
