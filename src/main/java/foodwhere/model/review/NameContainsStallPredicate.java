package foodwhere.model.review;

import java.util.List;
import java.util.function.Predicate;

import foodwhere.commons.util.StringUtil;

/**
 * Tests that a {@code Stall}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsStallPredicate implements Predicate<Review> {
    private final List<String> keywords;

    public NameContainsStallPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Review review) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(review.getName().fullName, keyword));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsStallPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsStallPredicate) other).keywords)); // state check
    }
}
