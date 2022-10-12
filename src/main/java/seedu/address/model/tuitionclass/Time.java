package seedu.address.model.tuitionclass;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents the timeslot of the tuition class.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be in LocalTime parsable format separted by dash \"-\"";
    public static final String VALIDATION_REGEX = "\\p{Digit}{2}:\\p{Digit}{2}-\\p{Digit}{2}:\\p{Digit}{2}";

    public final String timeFrame;

    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * The constructor for a Time object.
     *
     * @param startTime A string representing the start time of the timeslot of a tuition class.
     * @param endTime A string representing the end time of the timeslot of a tuition class.
     */
    public Time(String startTime, String endTime) throws ParseException {
        this.timeFrame = startTime + "-" + endTime;
        try {
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        if (this.startTime.compareTo(this.endTime) > 0) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
    }

    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    //TODO: add InvalidTimeFormatException

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
        return startTime.format(dtf) + " - " + endTime.format(dtf);
    }
}
