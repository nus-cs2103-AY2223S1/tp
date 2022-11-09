package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents when a Person usually goes online.
 */
public class TimeInterval implements ITimesAvailable {

    private static final String VALIDATION_REGEX =
            "(mon|tue|wed|thu|fri|sat|sun)@([0-9]{4}) *- *(mon|tue|wed|thu|fri|sat|sun)@([0-9]{4})";
    private static final String TIME_INTERVAL_CONSTRAINTS =
            "Time Interval should be in the form mon@2200-tue@2300";

    private final DayTimeInWeek startTime;
    private final DayTimeInWeek endTime;

    /**
     * Constructs a {@code TimeInterval}.
     * @param timeInterval A valid time interval in String, in the form of
     *                     day@time-day@time.
     */
    public TimeInterval(String timeInterval) {
        requireNonNull(timeInterval);
        checkArgument(isValidTimeInterval(timeInterval), TIME_INTERVAL_CONSTRAINTS);
        String startTime = getStartingDayTimeInWeek(timeInterval);
        checkArgument(DayTimeInWeek.isValidDayTimeInWeekParsing(startTime),
                DayTimeInWeek.ILLEGAL_TIME_CONSTRAINTS);
        String endTime = getEndingDayTimeInWeek(timeInterval);
        checkArgument(DayTimeInWeek.isValidDayTimeInWeekParsing(endTime),
                DayTimeInWeek.ILLEGAL_TIME_CONSTRAINTS);
        this.startTime = makeStartTime(startTime);
        this.endTime = makeEndTime(endTime);
    }

    /**
     * Returns the start time of the time interval in DayTimeInWeek.
     * @param startTime the valid DayTimeInWeek in String.
     * @return the start time in DayTimeInWeek.
     */
    public static DayTimeInWeek makeStartTime(String startTime) {
        return new DayTimeInWeek(startTime);
    }

    /**
     * Returns the end time of the time interval in DayTimeInWeek.
     * @param endTime the valid DayTimeInWeek in String.
     * @return the end time in DayTimeInWeek.
     */
    public static DayTimeInWeek makeEndTime(String endTime) {
        return new DayTimeInWeek(endTime);
    }

    /**
     * Returns true if the given String is a valid time interval.
     * @param test A string.
     * @return A boolean value.
     */
    public static boolean isValidTimeInterval(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the String representation of the point in time at which the given time interval starts.
     * @param test A time interval.
     * @return A start time.
     */
    public static String getStartingDayTimeInWeek(String test) {
        assert isValidTimeInterval(test);
        String[] tokens = test.split("-");
        return tokens[0].trim();
    }

    /**
     * Returns the String representation of the point in time at which the given time interval ends.
     * @param test A time interval.
     * @return An end time.
     */
    public static String getEndingDayTimeInWeek(String test) {
        assert isValidTimeInterval(test);
        String[] tokens = test.split("-");
        return tokens[1].trim();
    }

    public static String getTimeIntervalConstraints() {
        return TIME_INTERVAL_CONSTRAINTS;
    }

    /**
     * Returns true if the given {@code dayTimeInWeek} lies within this {@code TimeInterval}.
     * @param dayTimeInWeek A {@code DayTimeInWeek}.
     * @return A boolean value.
     */
    @Override
    public boolean isAvailable(DayTimeInWeek dayTimeInWeek) {
        // Must consider the case where user is available from Sunday night to Monday morning
        if (startTime.minutesSinceMondayMidnight > endTime.minutesSinceMondayMidnight) {
            return dayTimeInWeek.minutesSinceMondayMidnight >= startTime.minutesSinceMondayMidnight
                    || dayTimeInWeek.minutesSinceMondayMidnight <= endTime.minutesSinceMondayMidnight;
        } else {
            return dayTimeInWeek.minutesSinceMondayMidnight >= startTime.minutesSinceMondayMidnight
                    && dayTimeInWeek.minutesSinceMondayMidnight <= endTime.minutesSinceMondayMidnight;
        }
    }

    /**
     * Returns hashcode for purpose of the  {@link #equals(Object)} method.
     * @return The hashcode of the String representation of the object.
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return startTime + "-" + endTime;
    }

    /**
     * Checks if a TimeInterval object is equal to another
     * object.
     * @param other Another object of comparison.
     * @return true if they are of the same object
     *      or if they are different TimeInterval objects with the same start
     *      and end time.
     */
    @Override
    public boolean equals(Object other) {
        return this == other // short circuit if same object
                || (other instanceof TimeInterval // instanceof handles null
                && startTime.equals(((TimeInterval) other).startTime) // state check
                && endTime.equals(((TimeInterval) other).endTime));
    }
}
