package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a Tutorial's day in the ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class TutorialDay {

    public static final String MESSAGE_CONSTRAINTS =
            "Days should only contain numbers from 1 (Monday) to 7 (Sunday)";

    public static final String VALIDATION_REGEX = "[1-7]";

    public final DayOfWeek dayValue;

    /**
     * Constructs a {@code TutorialDay}.
     *
     * @param dayOfWeek A valid day.
     */
    public TutorialDay(String dayOfWeek) {
        requireNonNull(dayOfWeek);
        checkArgument(isValidDay(dayOfWeek), MESSAGE_CONSTRAINTS);
        dayValue = DayOfWeek.of(Integer.parseInt(dayOfWeek));
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns dayValue as a string of integer.
     */
    public String toIntString() {
        return String.valueOf(dayValue.getValue());
    }

    @Override
    public String toString() {
        return dayValue.getDisplayName(TextStyle.SHORT, Locale.UK);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialDay // instanceof handles nulls
                && dayValue.equals(((TutorialDay) other).dayValue)); // state check
    }

    @Override
    public int hashCode() {
        return dayValue.hashCode();
    }

}
