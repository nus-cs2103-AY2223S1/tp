package seedu.travelr.model.trip;

import java.util.List;
import java.util.function.Predicate;

import seedu.travelr.commons.util.StringUtil;

/**
 * Tests that a {@code Trip}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Trip> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Trip trip) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(trip.getTitle().fullTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
