package seedu.rc4hdb.model.venues.booking.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import java.util.List;

import seedu.rc4hdb.model.StringField;

/**
 * Represents a Booking's day in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day extends StringField implements BookingField {

    public static final String IDENTIFIER = "Day";

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only contain the first 3 letters of the day of the week. Day is also not case-sensitive";

    public static final List<String> DAYS_OF_WEEK = List.of("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

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
        return DAYS_OF_WEEK.stream().anyMatch(day -> day.equalsIgnoreCase(test));
    }

}
