package seedu.address.commons.core.keyword;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class KeywordList {
    Set<Keyword> keywords;

    public KeywordList() {
        keywords = new HashSet<Keyword>();
    }

    public void addKeyword(Keyword keyword) {
        requireNonNull(keyword);
        keywords.add(keyword);
    }

    public boolean isAnyKeywordFound(String string) {
        boolean result = false;

        for(Keyword k: keywords) {
            result = result || k.isKeywordFound(string);
        }

        return result;
    }

    public boolean isEmpty() {
        return keywords.size() == 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof KeywordList // instanceof handles nulls
                && keywords.equals(((KeywordList) other).keywords));
    }
}
