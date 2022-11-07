package seedu.rc4hdb.model.venues.booking.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Booking's start or end time in hours in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidHour(String)}
 */
public class Hour extends StringField {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should only contain integers between 8 and 23, and it should be in the format HH.";

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

    public String toString() {
        return hour + "00";
    }

    /**
     * Returns true if a given string is a valid hour.
     */
    public static boolean isValidHour(String test) {
        try {
            int hour = Integer.parseInt(test);
            return hour >= 8 && hour <= 23;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets the hour as an integer.
     */
    public int asInt() {
        return hour;
    }

    /**
     * Checks whether this hour comes after or is during the {@code other} hour.
     */
    public boolean isAfterOrDuring(Hour other) {
        return hour >= other.hour;
    }

}
