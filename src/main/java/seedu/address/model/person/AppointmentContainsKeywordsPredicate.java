package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appt) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(appt.getReason(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsKeywordsPredicate) other).keywords)); // state check
    }

}
