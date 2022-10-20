package seedu.intrack.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.intrack.commons.util.StringUtil;

/**
 * Tests that a {@code Internship}'s {@code Position} matches any of the keywords given.
 */
public class PositionContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public PositionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getPosition().positionName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PositionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
