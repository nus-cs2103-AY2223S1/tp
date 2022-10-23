package seedu.rc4hdb.model.venues.booking.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Booking's start or end time in hours in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidHour(String)}
 */
public class Hour extends StringField {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should only contain numbers, and it should be in the format HH";

    private final int hour;

    /**
     * Constructs a {@code Hour}.
     *
     * @param hour A valid hour
     */
    public Hour(String hour) {
        super(hour);
        checkArgument(isValidHour(hour), MESSAGE_CONSTRAINTS);
        this.hour = Integer.parseInt(hour);
    }

    /**
     * Returns true if a given string is a valid hour.
     */
    public static boolean isValidHour(String test) {
        try {
            int hour = Integer.parseInt(test);
            return hour >= 0 && hour < 24;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isAfterOrDuring(Hour other) {
        return hour >= other.hour;
    }

}
