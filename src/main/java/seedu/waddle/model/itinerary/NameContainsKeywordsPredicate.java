package seedu.waddle.model.itinerary;

import java.util.List;
import java.util.function.Predicate;

import seedu.waddle.commons.util.StringUtil;

/**
 * Tests that a {@code Itinerary}'s {@code Description} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Itinerary> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Itinerary itinerary) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(itinerary.getDescription()
                        .description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
