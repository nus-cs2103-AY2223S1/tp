package seedu.hrpro.model.staff;

import java.util.List;
import java.util.function.Predicate;

import seedu.hrpro.commons.util.StringUtil;

/**
 * Tests that a {@code Staff}'s {@code StaffName} matches any of the keywords given.
 */
public class StaffNameContainsKeywordsPredicate implements Predicate<Staff> {
    private final List<String> keywords;

    public StaffNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Staff staff) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsNameIgnoreCase(staff.getStaffName().staffName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StaffNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
