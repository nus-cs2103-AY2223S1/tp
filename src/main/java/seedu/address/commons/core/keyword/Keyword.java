package seedu.address.commons.core.keyword;

import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.Date;

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
