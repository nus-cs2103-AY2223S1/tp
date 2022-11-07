package seedu.rc4hdb.model.venues.booking.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;
import static seedu.rc4hdb.model.venues.booking.fields.Hour.isValidHour;

import seedu.rc4hdb.model.StringField;

/**
 * Represents an interval of time that denominated in hours.
 * Guarantees: immutable; is valid as declared in {@link #isValidHourPeriod(String)}
 */
public class HourPeriod extends StringField implements BookingField {

    public static final String IDENTIFIER = "HourPeriod";

    public static final String MESSAGE_CONSTRAINTS =
            "Time should only contain numbers, and it should be in the format HH-HH. The start hour cannot be larger "
                    + "or equal to the end hour. Start hour cannot be below 8 and end hour cannot be above 23.";

    private final Hour startHour;
    private final Hour endHour;

    /**
     * Constructs a {@code HourPeriod}.
     *
     * @param hourPeriodString A valid hourPeriodString.
     */
    public HourPeriod(String hourPeriodString) {
        super(hourPeriodString);
        checkArgument(isValidHourPeriod(hourPeriodString), MESSAGE_CONSTRAINTS);
        String[] hours = hourPeriodString.split("-");
        startHour = new Hour(hours[0]);
        endHour = new Hour(hours[1]);
    }

    public String toString() {
        return startHour + "-" + endHour;
    }

    /**
     * Returns true if a given string is a valid hour period.
     */
    public static boolean isValidHourPeriod(String test) {
        String[] hours = test.split("-");
        return hours.length == 2
                && isValidHour(hours[0])
                && isValidHour(hours[1])
                && Integer.parseInt(hours[0]) < Integer.parseInt(hours[1]);
    }

    /**
     * Checks if this HourPeriod clashes with the other HourPeriod.
     * @param other the other HourPeriod
     * @return true if both clashes with each other.
     */
    public boolean clashesWith(HourPeriod other) {
        return !(startHour.isAfterOrDuring(other.endHour)
                || other.startHour.isAfterOrDuring(endHour));
    }

    public Hour getStart() {
        return startHour;
    }

    public Hour getEnd() {
        return endHour;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HourPeriod // instanceof handles nulls
                && startHour.equals(((HourPeriod) other).startHour))
                && endHour.equals(((HourPeriod) other).endHour);
    }

}
