package seedu.guest.model.guest;

import java.util.List;
import java.util.function.Predicate;

import seedu.guest.commons.util.StringUtil;

/**
 * Tests that a {@code Guest}'s {@code Name} matches any of the keywords given.
 */
public class GuestContainsKeywordsPredicate implements Predicate<Guest> {
    private final List<String> keywords;

    public GuestContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Guest guest) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getName().fullName, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getPhone().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getEmail().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getRoom().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsDateRangeIgnoreHyphenIgnoreSpace(guest.getDateRange()
                        .toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getNumberOfGuests().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getIsRoomClean().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getBill().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getRequest().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GuestContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GuestContainsKeywordsPredicate) other).keywords)); // state check
    }

}
