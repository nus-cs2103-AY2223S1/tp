package seedu.address.model.datetime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a timeslot that repeats every week in ModQuik.
 * Guarantees: immutable
 */

public class WeeklyTimeslot {
    public static final String MESSAGE_CONSTRAINTS_TIMES =
            "Times should be in HH:mm format, e.g. 08:00";
    public static final String MESSAGE_CONSTRAINTS_DAY =
            "Days should only contain numbers from 1 (Monday) to 7 (Sunday)";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    public static final String MESSAGE_INVALID_DURATION = "The start time should be before end time.";

    public final DayOfWeek dayValue;
    public final LocalTime startTime;
    public final LocalTime endTime;


    public static final String VALIDATION_REGEX = "[1-7]";


    /**
     * Constructs a {@code WeeklyTimeslot}.
     *
     * @param dayString A day, represented by a number from 1 to 7 inclusive.
     * @param startTimeString A validly formatted time for start.
     * @param endTimeString A validly formatted time for end.
     */
    public WeeklyTimeslot(String dayString, String startTimeString, String endTimeString) {
        requireNonNull(dayString);
        requireNonNull(startTimeString);
        requireNonNull(endTimeString);
        checkArgument(isValidTime(startTimeString), MESSAGE_CONSTRAINTS_TIMES);
        checkArgument(isValidTime(endTimeString), MESSAGE_CONSTRAINTS_TIMES);
        checkArgument(isValidDay(dayString), MESSAGE_CONSTRAINTS_DAY);
        this.startTime = LocalTime.parse(startTimeString, DATE_TIME_FORMAT);
        this.endTime = LocalTime.parse(endTimeString, DATE_TIME_FORMAT);
        dayValue = DayOfWeek.of(Integer.parseInt(dayString));
    }

    public static boolean isValidTime(String timeString) {
        try {
            LocalTime.parse(timeString, DATE_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String dayString) {

        return dayString.matches(VALIDATION_REGEX);
    }

    private String getDayOfWeekReadable() {
        return dayValue.getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }

    @Override
    public String toString() {
        return String.format("%s %s to %s", getDayOfWeekReadable(), startTime, endTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WeeklyTimeslot // instanceof handles nulls
                && startTime.equals(((WeeklyTimeslot) other).startTime) // state check
                && endTime.equals(((WeeklyTimeslot) other).endTime) // state check
                && dayValue.equals(((WeeklyTimeslot) other).dayValue)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, dayValue);
    }

}
