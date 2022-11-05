package seedu.address.logic.parser;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Converts the current time to a String format.
 * Makes use of Java time api to obtain current time.
 */
public class CurrentTimeConverter {

    public static final DateTimeFormatter STANDARD_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Converts current time to String representation.
     * Current time is dependent on the time displayed on
     * your personal device.
     * @return A String representation of current time.
     */
    public static String findTimeNowInString() {
        LocalDateTime currentTime = LocalDateTime.now();
        DayOfWeek currentDay = currentTime.getDayOfWeek();
        String currentDayInString = convertDayOfWeekToString(currentDay);
        String formattedTime = currentTime.format(STANDARD_TIME_FORMAT);
        return currentDayInString + "@" + formattedTime;
    }

    /**
     * Converts current day to String representation.
     * Current time is dependent on the time displayed on
     * your personal device.
     * @param dayOfWeek The day of the week.
     * @return String representation of current day.
     */
    public static String convertDayOfWeekToString(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
        case MONDAY:
            return "mon";
        case TUESDAY:
            return "tue";
        case WEDNESDAY:
            return "wed";
        case THURSDAY:
            return "thu";
        case FRIDAY:
            return "fri";
        case SATURDAY:
            return "sat";
        case SUNDAY:
            return "sun";
        default:
            assert false;
            return "error";
        }
    }
}
