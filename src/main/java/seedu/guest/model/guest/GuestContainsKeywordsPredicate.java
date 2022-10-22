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

    private boolean compareWord(String sentence) {
        return keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(sentence, keyword));
    }

    private boolean compareDate(String sentence) {
        return keywords.stream().anyMatch(keyword -> StringUtil.containsDateIgnoreHyphenIgnoreSpace(sentence,
                keyword));
    }

    @Override
    public boolean test(Guest guest) {
        return compareWord(guest.getName().fullName)
                || compareWord(guest.getPhone().toString())
                || compareWord(guest.getEmail().toString())
                || compareWord(guest.getRoom().toString())
                || compareWord(guest.getNumberOfGuests().toString())
                || compareWord(guest.getIsRoomClean().toString())
                || compareWord(guest.getBill().toString())
                || compareWord(guest.getRequest().toString())
                || compareDate(guest.getDateRange().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GuestContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GuestContainsKeywordsPredicate) other).keywords)); // state check
    }

}
