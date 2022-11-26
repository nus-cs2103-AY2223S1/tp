package seedu.workbook.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.workbook.commons.util.StringUtil;

/**
 * Tests that a {@code Internship}'s {@code Role} matches any of the keywords given.
 */
public class RoleContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public RoleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getRole().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RoleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
