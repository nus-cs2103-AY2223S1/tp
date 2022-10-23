package seedu.waddle.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.AppUtil.checkArgument;

/**
 * Represents an Itinerary's day number element.
 * Guarantees: immutable; is valid as declared in {@link #isValidDayNumber(String)}
 */
public class DayNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "Day number should only contain positive numbers";
    public static final String VALIDATION_REGEX = "\\d+";

    public final int dayNumber;

    /**
     * Constructs a {@code DayNumber}.
     *
     * @param dayNumber A valid value.
     */
    public DayNumber(String dayNumber) {
        requireNonNull(dayNumber);
        checkArgument(isValidDayNumber(dayNumber), MESSAGE_CONSTRAINTS);
        this.dayNumber = Integer.parseInt(dayNumber);
    }

    /**
     * Returns true if a given string is a valid day number.
     */
    public static boolean isValidDayNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return String.valueOf(dayNumber);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.waddle.model.itinerary.DayNumber // instanceof handles nulls
                && dayNumber == ((seedu.waddle.model.itinerary.DayNumber) other).dayNumber); // state check
    }
}
