package seedu.intrack.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.intrack.commons.util.StringUtil;

/**
 * Tests that a {@code Internship}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
