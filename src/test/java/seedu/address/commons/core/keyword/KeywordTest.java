package seedu.address.commons.core.keyword;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
