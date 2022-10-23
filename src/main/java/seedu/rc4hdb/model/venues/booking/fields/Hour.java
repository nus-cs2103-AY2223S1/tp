package seedu.rc4hdb.model.venues.booking.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

/**
 * Represents a Booking's start or end time in hours in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidHour(String)}
 */
public class Hour extends BookingField {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should only contain numbers, and it should be in the format HH";

    /**
     * Constructs a {@code Hour}.
     *
     * @param hour A valid hour
     */
    public Hour(String hour) {
        super(hour);
        checkArgument(isValidHour(hour), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid hour.
     */
    public static boolean isValidHour(String test) {
        int hour = Integer.parseInt(test);
        return hour >= 0 && hour < 24;
    }

}
