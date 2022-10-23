package seedu.address.model.timeRange;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * Represents a TimeRange when searching for the next available time slot.
 * Guarantees: immmutable;
 */
public class TimeRange {
    public static final String MESSAGE_CONSTRAINTS = "TimeRange can take any string"
            + " in a '0000-2359 MINUTES' format"
            + "\nExamples:  1000-1300 120";
    public static final String VALIDATION_TIME_REGEX = "[0-9]{4}";
    public static final String VALIDATION_DURATION_REGEX = "[0-9]*";
    public static final String VALIDATION_TIME_RANGE_REGEX = VALIDATION_TIME_REGEX + "-" + VALIDATION_TIME_REGEX
            + " " + VALIDATION_DURATION_REGEX;
    public static final String INVALID_DURATION_ERROR_MESSAGE = "EndTime must be after StartTime "
        + "and duration must be less than the difference between EndTime and StartTime";
    public final LocalTime startTimeRange;
    public final LocalTime endTimeRange;
    public final Integer duration;

    /**
     * Constructs a {@code TimeRange}.
     *
     * @param startTimeRange LocalTime object.
     * @param endTimeRange LocalTime object.
     * @param duration expected duration of the Class session.
     */
    public TimeRange(LocalTime startTimeRange, LocalTime endTimeRange, Integer duration) {
        requireAllNonNull(startTimeRange, endTimeRange, duration);
        this.startTimeRange = startTimeRange;
        this.endTimeRange = endTimeRange;
        this.duration = duration;
    }

    /**
     * Returns true if a given string is a valid input.
     *
     * @param dateTimeRange String to be validated.
     * @return True if a given string fits the format of '0000-2359'.
     */
    public static boolean isValidTimeRange(String dateTimeRange) {
        if (!dateTimeRange.matches(VALIDATION_TIME_RANGE_REGEX)) {
            return false;
        }
        // 1000-1200 120
        String startTimeStr = dateTimeRange.substring(0, 4);
        String endTimeStr = dateTimeRange.substring(5, 9);
        return isValidTimeString(startTimeStr) && isValidTimeString(endTimeStr);
    }

    /**
     * Returns true if a given string is a valid time.
     *
     * @param time String object.
     * @return True if is valid.
     */
    private static boolean isValidTimeString(String time) {
        Integer hour = Integer.valueOf(time.substring(0, 2));
        Integer minute = Integer.valueOf(time.substring(2));
        try {
            LocalTime.of(hour, minute);
        } catch (DateTimeException dateTimeException) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the expected end time is before the {@code endTimeRange}
     * @param startTime a LocalTime object.
     * @param endTime a LocalTime object.
     * @param duration an Integer object.
     * @return true if it is valid.
     */
    public static boolean isValidEndTime(LocalTime startTime, LocalTime endTime, Integer duration) {
        LocalTime expectedEndTime = startTime.plusMinutes(duration);
        return expectedEndTime.compareTo(endTime) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeRange // instanceof handles nulls
                && startTimeRange.equals(((TimeRange) other).startTimeRange)
                && endTimeRange.equals(((TimeRange) other).endTimeRange)
                && duration.equals(((TimeRange) other).duration)); // state check
    }
}
