package seedu.address.model.staff;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Staff}'s {@code StaffName} matches any of the keywords given.
 */
public class StaffNameContainsKeywordsPredicate implements Predicate<Staff> {
    private final String keyword;

    public StaffNameContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Staff staff) {
        return StringUtil.containsNameIgnoreCase(staff.getStaffName().staffName,
                keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffNameContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((StaffNameContainsKeywordsPredicate) other).keyword)); // state check
    }
}
