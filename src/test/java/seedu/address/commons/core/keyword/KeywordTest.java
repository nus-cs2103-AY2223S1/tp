package seedu.address.commons.core.keyword;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KeywordTest {
    @Test
    public void equals() {
        String firstWord = "test1";
        String secondWord = "test2";

        Keyword firstKeyword = new Keyword(firstWord);
        Keyword secondKeyword = new Keyword(secondWord);

        // same object -> return true
        assertTrue(firstKeyword.equals(firstKeyword));

        // same values -> return true
        assertTrue(firstKeyword.equals(new Keyword(firstWord)));

        // different type -> return false
        assertFalse(firstKeyword.equals(2814));

        // null -> return false
        assertFalse(firstKeyword.equals(null));

        // different value -> return false
        assertFalse(firstKeyword.equals(secondKeyword));
    }

    @Test
    public void isKeywordFound() {
        Keyword keyword = new Keyword("test");

        // empty string -> return false
        assertFalse(keyword.isKeywordFound(""));

        // keyword not found -> return false
        assertFalse(keyword.isKeywordFound("aiqbfwiwbseg"));

        // exact match -> return true
        assertTrue(keyword.isKeywordFound("test"));

        // keyword contained -> return true
        assertTrue(keyword.isKeywordFound("912941testing90490193"));
    }

    @Test
    public void isDate() {
        // not a date -> returns false
        assertFalse(new Keyword("1284nwef").isDate());

        // date in dd-mm-yyyy format -> returns true
        assertTrue(new Keyword("02-12-2022").isDate());

        // date not in dd-mm-yyyy format -> returns false
        assertFalse(new Keyword("2022-02-02").isDate());
        assertFalse(new Keyword("10 Aug 2021").isDate());
        assertFalse(new Keyword("12-30-2021").isDate());
    }
}
