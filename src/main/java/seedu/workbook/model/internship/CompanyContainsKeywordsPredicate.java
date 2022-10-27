package seedu.workbook.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.workbook.commons.util.StringUtil;

/**
 * Tests that a {@code Internship}'s {@code Company} matches any of the keywords given.
 */
public class CompanyContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public CompanyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getCompany().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CompanyContainsKeywordsPredicate) other).keywords)); // state check
    }

}
