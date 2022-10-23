package seedu.rc4hdb.model.venues;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;

import seedu.rc4hdb.model.resident.fields.Field;



/**
 * Represents a Booking's day in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only contain numbers from 1-7";

    public static final String VALIDATION_REGEX = "^[1-7]$";
    public final DayOfWeek dayOfWeek;

    /**
     * Constructs a {@code Day}.
     *
     * @param dayOfWeek A valid day
     */
    public Day(String dayOfWeek) {
        requireNonNull(dayOfWeek);
        checkArgument(isValidDay(dayOfWeek), MESSAGE_CONSTRAINTS);
        this.dayOfWeek = generateDayOfWeek(dayOfWeek);
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private DayOfWeek generateDayOfWeek(String time) {
        requireNonNull(time);
        return DayOfWeek.of(Integer.parseInt(time));
    }

    @Override
    public String toString() {
        return dayOfWeek.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && dayOfWeek.equals(((Day) other).dayOfWeek)); // state check
    }

    @Override
    public int hashCode() {
        return dayOfWeek.hashCode();
    }

}
