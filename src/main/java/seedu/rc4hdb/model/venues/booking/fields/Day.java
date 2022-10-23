package seedu.rc4hdb.model.venues.booking.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

/**
 * Represents a Booking's day in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day extends BookingField {

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only contain the first 3 letters of the day of the week. Day is also not case-sensitive";

    /**
     * Constructs a {@code Day}.
     *
     * @param dayOfWeek A valid day
     */
    public Day(String dayOfWeek) {
        super(dayOfWeek.toUpperCase());
        checkArgument(isValidDay(dayOfWeek), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String test) {
        switch (test.toUpperCase()) {
        case "MON": case "TUE": case "WED": case "THU": case "FRI": case "SAT": case "SUN":
            return true;
        default:
            return false;
        }
    }

}
