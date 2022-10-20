package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Internship}'s {@code Company} matches any of the keywords given.
 */
public class CompanyContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public CompanyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getCompany().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CompanyContainsKeywordsPredicate) other).keywords)); // state check
    }

}
