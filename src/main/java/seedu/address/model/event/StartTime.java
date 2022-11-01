package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalTime;


/**
 * Represents an Event's starting time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime implements Comparable<StartTime> {

    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Start time must be in format: hh:mm";
    public static final String MESSAGE_VALUE_CONSTRAINTS = "%s exceeds the range of valid time value.";
    private static final DecimalFormat DF = new DecimalFormat("00");
    public final LocalTime time;


    /**
     * Constructs a {@code StartTime}.
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        assert !startTime.isBlank();
        checkArgument(isValidStartTime(startTime) != 0, MESSAGE_FORMAT_CONSTRAINTS);
        checkArgument(isValidStartTime(startTime) != -1, String.format(MESSAGE_VALUE_CONSTRAINTS, startTime));
        String [] parsedTime = startTime.split(":", 2);
        this.time = LocalTime.of(Integer.parseInt(parsedTime[0]), Integer.parseInt(parsedTime[1]), 0);
    }

    /**
     * Checks if a given string is a valid StartTime input.
     * @return int value, 0 represent invalid time format, -1 represents invalid time value, 1 represents valid time.
     */
    public static int isValidStartTime(String test) {
        try {
            String [] parsedTime = test.split(":", 2);
            Integer formatCheck = Integer.parseInt(parsedTime[0]) + Integer.parseInt(parsedTime[1]);
            boolean lengthCheck = parsedTime[0].length() == 2 && parsedTime[1].length() == 2;
            LocalTime.of(Integer.parseInt(parsedTime[0]), Integer.parseInt(parsedTime[1]), 0);
            if (!lengthCheck) {
                return 0;
            }
        } catch (DateTimeException e) {
            return -1;
        } catch (NumberFormatException e) {
            return 0;
        }
        return 1;
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
        return String.format("%s:%s", df.format(this.time.getHour()), df.format(this.time.getMinute()));
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
        return String.format("%s:%s", df.format(this.time.getHour()), df.format(this.time.getMinute()));
    }

    @Override
    public int hashCode() {
        return this.time.hashCode();
    }
}
