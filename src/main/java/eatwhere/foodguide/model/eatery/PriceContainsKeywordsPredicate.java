package eatwhere.foodguide.model.eatery;

import java.util.List;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.util.StringUtil;

/**
 * Tests that a {@code Eatery}'s {@code Name} matches any of the keywords given.
 */
public class PriceContainsKeywordsPredicate implements Predicate<Eatery> {

    private final List<String> keywords;

    public PriceContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Eatery eatery) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(eatery.getPrice().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PriceContainsKeywordsPredicate) other).keywords)); // state check
    }

}
