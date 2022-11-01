package seedu.address.model.listing;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Listing}'s {@code Id} matches any of the keywords given.
 */
public class IdContainsKeywordsPredicate implements Predicate<Listing> {
    private final String keyword;

    public IdContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Listing listing) {
        return StringUtil.containsWordIgnoreCase(listing.getId().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((IdContainsKeywordsPredicate) other).keyword)); // state check
    }

}
