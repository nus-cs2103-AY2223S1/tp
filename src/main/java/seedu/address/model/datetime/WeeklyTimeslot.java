package seedu.address.model.datetime;

import java.time.DayOfWeek;
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
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
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
        this.startTime = LocalTime.parse(startTimeString, TIME_FORMAT);
        this.endTime = LocalTime.parse(endTimeString, TIME_FORMAT);
        dayValue = DayOfWeek.of(Integer.parseInt(dayString));
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String dayString) {

        return dayString.matches(VALIDATION_REGEX);
    }

    private static boolean isValidTime(String datetime) {
        try {
            LocalTime.parse(datetime, TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if given string is valid as a time range.
     *
     * @param timeStart Starting time
     * @param timeEnd Ending time
     * @return Whether both strings are valid when taken together as a time range
     */
    public static boolean isValidTimeRange(String timeStart, String timeEnd) {
        // Unideal: does additional work by creating objects to check validity.
        // This is to confirm to the rest of the codebase, where checks are done on a
        // "check for permission" in a static method beforehand.
        if (!isValidTime(timeStart) || !isValidTime(timeEnd)) {
            return false;
        }
        LocalTime timeStartObj = LocalTime.parse(timeStart, TIME_FORMAT);
        LocalTime timeEndObj = LocalTime.parse(timeEnd, TIME_FORMAT);
        return !timeEndObj.isBefore(timeStartObj);
    }


    /**
     * Returns the numerical value of the day of the week.
     *
     * @return Day of the week
     */
    public String getDay() {
        return String.valueOf(dayValue.getValue());
    }

    /**
     * Get formatted start time.
     *
     * @return Formatted start time
     */
    public String getStartTimeFormatted() {
        return startTime.format(TIME_FORMAT);
    }

    /**
     * Get formatted end time.
     *
     * @return Formatted end time
     */
    public String getEndTimeFormatted() {
        return endTime.format(TIME_FORMAT);
    }


    /**
     * Get day of week in an abbreviated human-friendly form.
     *
     * @return Day of the week
     */
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
