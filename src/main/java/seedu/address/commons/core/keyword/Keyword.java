package seedu.address.commons.core.keyword;

import static java.util.Objects.requireNonNull;

public class Keyword {
    String keyword;

    public Keyword(String keyword) {
        requireNonNull(keyword);
        this.keyword = keyword;
    }

    public boolean isKeywordFound(String string) {
        return string.toLowerCase().contains(keyword.toLowerCase());
    }
}
