package seedu.phu.commons.core.keyword;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a collection of Keywords Object.
 */
public class KeywordList {
    private Set<Keyword> keywords;

    /**
     * Constructs an empty collection of Keywords.
     */
    public KeywordList() {
        keywords = new HashSet<Keyword>();
    }

    /**
     * Add a Keyword to the collection of Keywords.
     * There will be no effect if Keyword already exist.
     *
     * @param keyword the keyword.
     */
    public void addKeyword(Keyword keyword) {
        requireNonNull(keyword);
        keywords.add(keyword);
    }

    /**
     * Test if the string matches with any of the Keywords
     * in the collection.
     *
     * @param string the string to test
     * @return whether it matches with one of the Keywords.
     */
    public boolean isAnyKeywordFound(String string) {
        boolean result = false;

        for (Keyword k: keywords) {
            result = result || k.isKeywordFound(string);
        }

        return result;
    }

    /**
     * Tests whether there is any Keyword found in the collection
     *
     * @return whether the collection is empty
     */
    public boolean isEmpty() {
        return keywords.size() == 0;
    }

    /**
     * Tests whether all Keyword is a valid application process.
     *
     * @return boolean value
     */
    public boolean isAllValidProcess() {
        boolean result = true;

        for (Keyword k: keywords) {
            result = result && k.isValidApplicationProcess();
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof KeywordList // instanceof handles nulls
                && keywords.equals(((KeywordList) other).keywords));
    }

    /**
     * Tests if all the Keyword is a valid date in
     * dd-mm-yyyy format
     *
     * @return the test result.
     */
    public boolean isAllKeywordDate() {
        boolean result = true;

        for (Keyword k: keywords) {
            result = result && k.isDate();
        }

        return result;
    }
}
