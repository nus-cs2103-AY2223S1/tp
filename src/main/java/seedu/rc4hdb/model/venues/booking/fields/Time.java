package seedu.rc4hdb.model.venues.booking.fields;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

import seedu.rc4hdb.model.Field;

/**
 * Represents a Booking's start or end time in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time extends Field {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should only contain numbers, and it should be in the format HH:MM";

    public static final String VALIDATION_REGEX = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
    public final LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time
     */
    public Time(String time) {
        super(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = generateTime(time);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private LocalTime generateTime(String time) {
        requireNonNull(time);
        return LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return time.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}
