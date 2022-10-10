package seedu.address.commons.core.keyword;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class KeywordList {
    ArrayList<Keyword> keywords;

    public KeywordList() {
        keywords = new ArrayList<Keyword>();
    }

    public void addKeyword(Keyword keyword) {
        requireNonNull(keyword);
        keywords.add(keyword);
    }

    public boolean isAnyKeywordFound(String string) {
        boolean result = false;

        for(int i = 0; i < keywords.size(); ++i) {
            result = result || keywords.get(i).isKeywordFound(string);
        }

        return result;
    }

    public boolean isEmpty() {
        return keywords.size() == 0;
    }
}
