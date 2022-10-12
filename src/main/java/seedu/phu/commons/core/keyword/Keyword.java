package seedu.phu.commons.core.keyword;

import static java.util.Objects.requireNonNull;

import seedu.phu.model.internship.ContainsKeywordsPredicate;
import seedu.phu.model.internship.Date;

/**
 * Represent a String of keyword.
 */
public class Keyword {
    private String keyword;

    /**
     * Constructs a new Keyword Object representing the given keyword.
     *
     * @param keyword a String.
     */
    public Keyword(String keyword) {
        requireNonNull(keyword);
        this.keyword = keyword;
    }

    public boolean isKeywordFound(String string) {
        return string.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((Keyword) other).keyword)); // state check
    }

    public boolean isDate() {
        return Date.isValidDate(keyword);
    }
}
