package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the day of week and time of day together.
 */
public class DayTimeInWeek {

    public static final int MINUTES_IN_A_WEEK = 60 * 24 * 7;
    public static final int MINUTES_IN_A_DAY = 60 * 24;
    public static final int MINUTES_IN_AN_HOUR = 60;

    public static final String MESSAGE_CONSTRAINTS =
            "Day time in week must be in the form fri@1200.";

    public static final String ILLEGAL_TIME_CONSTRAINTS =
            "Time must be in valid format! For example, 12.30am should be written as 0030" +
                    " and there cannot be more than 60 minutes in one hour.";

    /*
     * Completely no whitespace in the name
     */
    public static final String VALIDATION_REGEX = "(mon|tue|wed|thu|fri|sat|sun)@([0-9]{4})";
    public static final String CURRENT_TIME_COMMAND = "now";

    public final Integer minutesSinceMondayMidnight;

    /**
     * Constructs a {@code DayTimeInWeek}.
     *
     * @param dayTimeInWeek A valid String representing a time in the week.
     */
    public DayTimeInWeek(String dayTimeInWeek) {
        requireNonNull(dayTimeInWeek);
        checkArgument(isValidDayTimeInWeekRegex(dayTimeInWeek), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDayTimeInWeekParsing(dayTimeInWeek), ILLEGAL_TIME_CONSTRAINTS);
        this.minutesSinceMondayMidnight = stringToMinutesSinceMondayMidnight(dayTimeInWeek);
    }

    /**
     * Returns true if a given string is matches the valid
     * regex for DayTimeInWeek.
     */
    public static boolean isValidDayTimeInWeekRegex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string can be converted to
     * a DayTimeInWeek.
     * @param test The given String.
     * @return true if the String can be converted to a DayTimeInWeek.
     */
    public static boolean isValidDayTimeInWeekParsing(String test) {
        String[] tokens = test.split("@");
        String timeOfDay = tokens[1];

        int minutes = Integer.parseInt(timeOfDay.substring(2, 4));
        if (minutes < 0 || minutes >= 60) {
            return false;
        }

        if (stringToMinutesSinceMidnight(timeOfDay) >= MINUTES_IN_A_DAY) {
            return false;
        }

        return stringToMinutesSinceMondayMidnight(test) < MINUTES_IN_A_WEEK;
    }

    public static boolean isFindTimeNow(String test) {
        return test.equals(CURRENT_TIME_COMMAND);
    }

    /**
     * Converts the String representation of TimeOfDay to minutes since midnight.
     * @param timeOfDay A String representation of the TimeOfDay.
     * @return Minutes elapsed since midnight.
     */
    private static int stringToMinutesSinceMidnight(String timeOfDay) {

        String hourStr = timeOfDay.substring(0, 2);
        String minuteStr = timeOfDay.substring(2, 4);
        int hour = Integer.parseInt(hourStr);
        int minute = Integer.parseInt(minuteStr);
        return hour * 60 + minute;

    }

    /**
     * Converts the String representation of DayTimeInWeek to minutes since midnight.
     * @param s A String representation of the DayTimeInWeek.
     * @return Minutes elapsed since Monday midnight.
     */
    private static int stringToMinutesSinceMondayMidnight(String s) {

        String[] tokens = s.split("@");
        String dayOfWeek = tokens[0];
        String timeOfDay = tokens[1];

        int dayOfWeekInt = getDayOfWeekNumeric(dayOfWeek);
        String hourStr = timeOfDay.substring(0, 2);
        String minuteStr = timeOfDay.substring(2, 4);
        int hourInt = Integer.parseInt(hourStr);
        int minuteInt = Integer.parseInt(minuteStr);

        return dayOfWeekInt * MINUTES_IN_A_DAY + hourInt * MINUTES_IN_AN_HOUR + minuteInt;

    }

    /**
     * Gets the number of days passed since Monday.
     * @param dayOfWeek A valid dayOfWeek
     * @return Number of days passed since Monday.
     */
    public static int getDayOfWeekNumeric(String dayOfWeek) {

        switch (dayOfWeek) {
        case "mon":
            return 0;
        case "tue":
            return 1;
        case "wed":
            return 2;
        case "thu":
            return 3;
        case "fri":
            return 4;
        case "sat":
            return 5;
        case "sun":
            return 6;
        default:
            assert false;
            return -1;
        }

    }

    /**
     * Gets the day of week in String.
     * @param dayOfWeek A valid dayOfWeek
     * @return String representation of the day of week.
     */
    public static String getDayOfWeekString(int dayOfWeek) {

        switch (dayOfWeek) {
        case 0:
            return "mon";
        case 1:
            return "tue";
        case 2:
            return "wed";
        case 3:
            return "thu";
        case 4:
            return "fri";
        case 5:
            return "sat";
        case 6:
            return "sun";
        default:
            assert false;
            return "???";
        }
    }

    @Override
    public String toString() {
        int day = minutesSinceMondayMidnight / MINUTES_IN_A_DAY;
        int minutesSinceMidnight = minutesSinceMondayMidnight % MINUTES_IN_A_DAY;
        int hour = minutesSinceMidnight / MINUTES_IN_AN_HOUR;
        int minute = minutesSinceMidnight % MINUTES_IN_AN_HOUR;
        return getDayOfWeekString(day) + "@" + String.format("%02d%02d", hour, minute);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DayTimeInWeek // instanceof handles nulls
                && minutesSinceMondayMidnight.equals(((DayTimeInWeek) other)
                .minutesSinceMondayMidnight)); // state check
    }

    @Override
    public int hashCode() {
        return minutesSinceMondayMidnight.hashCode();
    }

}
