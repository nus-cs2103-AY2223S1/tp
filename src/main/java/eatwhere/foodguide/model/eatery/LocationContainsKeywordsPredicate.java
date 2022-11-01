package eatwhere.foodguide.model.eatery;

import java.util.List;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.util.StringUtil;

/**
 * Tests that a {@code Eatery}'s {@code Location} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<Eatery> {
    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Eatery eatery) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(eatery.getLocation().value
                .replaceAll("\\pP", ""), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LocationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
