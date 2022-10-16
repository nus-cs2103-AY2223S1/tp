package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {

    public enum hideBy {
        TAG, KEYWORD, IS_MARKED;
    }
    private final hideBy condition;
    private List<String> keywords;

    public AppointmentContainsKeywordsPredicate(hideBy condition, List<String> keywords) {
        this.condition = condition;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appt) {
        switch (condition) {
            case hideBy.KEYWORD:

        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsKeywordsPredicate) other).keywords)); // state check
    }

}
