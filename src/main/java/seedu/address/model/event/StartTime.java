package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalTime;


/**
 * Represents an Event's starting time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTimeFormat(String)} and
 * {@link #isValidStartTimeValue(String)}
 */
public class StartTime implements Comparable<StartTime> {

    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Start time must be in format: hh:mm,"
            + " hh and mm must be a zero padded number of length 2, e.g. 02:30";
    public static final String MESSAGE_VALUE_CONSTRAINTS = "%s exceeds the range of valid time value.";
    private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("00");
    private static final String TIME_DELIMITER = ":";
    private static final String VALIDATION_REGEX = "\\d{2}:\\d{2}";
    public final LocalTime time;

    /**
     * Constructs a {@code StartTime}.
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        assert !startTime.isBlank();
        checkArgument(isValidStartTimeFormat(startTime), MESSAGE_FORMAT_CONSTRAINTS);
        checkArgument(isValidStartTimeValue(startTime), String.format(MESSAGE_VALUE_CONSTRAINTS, startTime));
        String[] parsedTime = startTime.split(TIME_DELIMITER, 2);
        this.time = LocalTime.of(Integer.parseInt(parsedTime[0]), Integer.parseInt(parsedTime[1]));
    }

    /**
     * Checks if a given string follows the valid StartTime input format.
     * @return boolean value.
     */
    public static boolean isValidStartTimeFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if a given string can be parsed to a valid StartTime value.
     * @param test string input, the format check for test should be done before calling this function.
     * @return boolean value.
     */
    public static boolean isValidStartTimeValue(String test) {
        try {
            String[] parsedTime = test.split(TIME_DELIMITER, 2);
            LocalTime.of(Integer.parseInt(parsedTime[0]), Integer.parseInt(parsedTime[1]));
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }


    @Override
    public int compareTo(StartTime t) {
        return this.time.compareTo(t.time);
    }

    /**
     * Returns the String representation of the StartTime for storage logging in the format of hh:mm.
     * @return String
     */
    public String toLogFormat() {
        return String.format("%s:%s", TIME_FORMATTER.format(this.time.getHour()),
                TIME_FORMATTER.format(this.time.getMinute()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StartTime)) {
            return false;
        }

        StartTime st = (StartTime) other;
        return this.time.equals(st.time);
    }

    @Override
    public String toString() {
        return toLogFormat();
    }

    @Override
    public int hashCode() {
        return this.time.hashCode();
    }
}
