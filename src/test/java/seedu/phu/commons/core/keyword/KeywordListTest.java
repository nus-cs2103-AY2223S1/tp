package seedu.phu.commons.core.keyword;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

    @Test
    public void isEmpty() {
        KeywordList emptyList = prepareList();
        KeywordList nonEmptyList = prepareList("some", "keywords");

        // empty list -> returns true
        assertTrue(emptyList.isEmpty());

        // non-empty list -> returns false
        assertFalse(nonEmptyList.isEmpty());
    }

    @Test
    public void isAnyKeywordFound() {
        KeywordList keywords = prepareList("leet", "code", "forces");

        // does not match any keyword -> returns false
        assertFalse(keywords.isAnyKeywordFound("optiver"));

        // empty string -> returns false
        assertFalse(keywords.isAnyKeywordFound(""));

        // match all keyword -> returns true
        assertTrue(keywords.isAnyKeywordFound("leetcodeforces"));

        // math one of the keyword -> returns ture
        assertTrue(keywords.isAnyKeywordFound("MayTheForcesBeWithYou"));
    }

    @Test
    public void isAllKeywordDate() {
        // one or more invalid date format -> returns false
        assertFalse(prepareList("02-02-2022", "2022-02-02").isAllKeywordDate());
        assertFalse(prepareList("msokdmvm", "02-02-2022").isAllKeywordDate());

        // all keywords is in valid date format -> returns true
        assertTrue(prepareList("02-02-2022", "01-02-2022", "03-12-2012").isAllKeywordDate());
    }

    private KeywordList prepareList(String ...args) {
        KeywordList keywords = new KeywordList();

        for (String s : args) {
            keywords.addKeyword(new Keyword(s));
        }

        return keywords;
    }
}
