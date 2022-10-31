package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents when a Person usually goes online.
 */
public class TimeInterval implements ITimesAvailable {

    private static final String VALIDATION_REGEX =
            "(mon|tue|wed|thu|fri|sat|sun)@([0-9]{4})-(mon|tue|wed|thu|fri|sat|sun)@([0-9]{4})";
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
        this.startTime = makeStartTime(timeInterval);
        this.endTime = makeEndTime(timeInterval);
    }

    /**
     * Returns the start time of the time interval in DayTimeInWeek.
     * @param timeInterval the valid time interval in String.
     * @return the start time in DayTimeInWeek.
     */
    public static DayTimeInWeek makeStartTime(String timeInterval) {
        String[] tokens = timeInterval.split("-");
        String start = tokens[0];
        return new DayTimeInWeek(start);
    }

    /**
     * Returns the end time of the time interval in DayTimeInWeek.
     * @param timeInterval the valid time interval in String.
     * @return the end time in DayTimeInWeek.
     */
    public static DayTimeInWeek makeEndTime(String timeInterval) {
        String[] tokens = timeInterval.split("-");
        String end = tokens[1];
        return new DayTimeInWeek(end);
    }

    /**
     * Returns true if the given String is a valid time interval.
     * @param test Another given String.
     * @return A boolean value.
     */
    public static boolean isValidTimeInterval(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static String getStartingDayTimeInWeek(String test) {
        assert isValidTimeInterval(test);
        String[] tokens = test.split("-");
        return tokens[0].trim();
    }

    public static String getEndingDayTimeInWeek(String test) {
        assert isValidTimeInterval(test);
        String[] tokens = test.split("-");
        return tokens[1].trim();
    }

    public static String getTimeIntervalConstraints() {
        return TIME_INTERVAL_CONSTRAINTS;
    }

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

    @Override
    public String toString() {
        return startTime + "-" + endTime;
    }

    @Override
    public boolean equals(Object other) {
        return this == other // short circuit if same object
                || (other instanceof TimeInterval // instanceof handles null
                && startTime.equals(((TimeInterval) other).startTime) // state check
                && endTime.equals(((TimeInterval) other).endTime));
    }
}
