package seedu.address.commons.core.keyword;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KeywordListTest {
    @Test
    public void equals() {
        KeywordList firstList = prepareList("a", "b", "c");
        KeywordList secondList = prepareList("c", "b", "a");
        KeywordList thirdList = prepareList("sf", "wegweg");
        KeywordList fourthList = prepareList("a", "b", "c", "c");

        // same object -> return true
        assertTrue(firstList.equals(firstList));

        // same values -> return true
        assertTrue(firstList.equals(prepareList("a", "b", "c")));

        // different type -> return false
        assertFalse(firstList.equals(37591832));

        // null -> return true
        assertFalse(firstList.equals(null));

        // same values with different order -> return true
        assertTrue(firstList.equals(secondList));

        // different values -> return false
        assertFalse(firstList.equals(thirdList));

        // same values with duplicates -> return true
        assertTrue(firstList.equals(fourthList));
    }

    private KeywordList prepareList(String ...args) {
        KeywordList keywords = new KeywordList();

        for(String s : args) {
            keywords.addKeyword(new Keyword(s));
        }

        return keywords;
    }
}
