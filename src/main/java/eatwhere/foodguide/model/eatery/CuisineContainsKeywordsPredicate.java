package eatwhere.foodguide.model.eatery;

import java.util.List;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.util.StringUtil;

/**
 * Tests that a {@code Eatery}'s {@code cuisine} matches any of the keywords given.
 */
public class CuisineContainsKeywordsPredicate implements Predicate<Eatery> {
    private final List<String> keywords;

    public CuisineContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Eatery eatery) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(eatery.getCuisine().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CuisineContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CuisineContainsKeywordsPredicate) other).keywords)); // state check
    }

}
