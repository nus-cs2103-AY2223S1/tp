package seedu.address.model.tuitionclass;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the timeslot of the tuition class.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Timings should be separated by a dash, a space or \"to\", and adhere to the following constraints:\n "
                    + "1. Timings must be in either 12-hour or 24-hour formats. When using the 12-hour format, "
                    + "AM/PM must be specified. The minutes (12-hour format only), colon and initial zero may be "
                    + "omitted.\n"
                    + "2. Start and end timings specified must respect chronology. The end time cannot occur before "
                    + "the start time.\n"
                    + "Some valid examples are:\n"
                    + "    - 12pm - 3pm\n"
                    + "    - 1:00pm 2:00pm\n"
                    + "    - 2200 to 2330\n";

    public static final String VALIDATION_REGEX =
            "((\\p{Digit}{1,2}(am|pm))|"
                    + "(\\p{Digit}{1,2}:{0,1}\\p{Digit}{2}"
                    + "(am|pm){0,1}))"
                    + "\\s*(-|to|\\s)\\s*"
                    + "((\\p{Digit}{1,2}(am|pm))|"
                    + "(\\p{Digit}{1,2}:{0,1}\\p{Digit}{2}"
                    + "(am|pm){0,1}))";

    public final String timeFrame;

    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * The constructor for a Time object.
     *
     * @param startTime A string representing the start time of the timeslot of a tuition class.
     * @param endTime   A string representing the end time of the timeslot of a tuition class.
     */
    public Time(String startTime, String endTime) throws IllegalArgumentException {
        requireAllNonNull(startTime, endTime);
        this.timeFrame = startTime + "-" + endTime;
        try {
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(Time.MESSAGE_CONSTRAINTS);
        }
        if (!(this.endTime.equals(LocalTime.parse("00:00")))
                && this.startTime.compareTo(this.endTime) > 0) { //so that time intervals can end with midnight
            throw new IllegalArgumentException(Time.MESSAGE_CONSTRAINTS);
        }
    }

    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of StartTime.
     */
    public String getStartTime() {
        return startTime.toString();
    }

    /**
     * Returns the string representation of EndTime.
     */
    public String getEndTime() {
        return endTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof Time) {
                Time casted = (Time) other;
                return startTime.equals(casted.startTime) && endTime.equals(casted.endTime);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return startTime.format(dtf) + "-" + endTime.format(dtf);
    }
}
