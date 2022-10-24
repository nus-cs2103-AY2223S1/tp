package seedu.trackascholar.model.applicant;

import java.util.List;
import java.util.function.Predicate;

import seedu.trackascholar.commons.util.StringUtil;

/**
 * Tests that a {@code Applicant}'s {@code Scholarship} matches any of the keywords given.
 */
public class ScholarshipContainsKeywordsPredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    public ScholarshipContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return keywords.stream().anyMatch(
                keyword -> StringUtil.containsWordIgnoreCase(applicant.getScholarship().scholarship, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScholarshipContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ScholarshipContainsKeywordsPredicate) other).keywords)); // state check
    }
}
