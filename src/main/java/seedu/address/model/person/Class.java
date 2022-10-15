package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's class date in the address book.
 * Guarantees: immutable;
 * is valid as declared in {@link #isValidClassString(String)}, {@link #isValidFlexibleClassString(String)}
 */
public class Class {

    public static final String MESSAGE_CONSTRAINTS = "Class can take any string"
            + " in either 'yyyy-MM-dd 0000-2359' or Day-of-Week 0000-2359 format"
            + "\nExamples:  2022-10-30 1000-1300,  Mon 1000-1300,  tue 1000-1300"
            + "\nDay-of-Week must be 3 letters and is case-insensitive";
    public static final String INVALID_DATETIME_ERROR_MESSAGE = "Date should be a valid date";
    public static final String INVALID_TIME_ERROR_MESSAGE = "Time should be in the range of 0000 - 2359";
    public static final String INVALID_DURATION_ERROR_MESSAGE = "EndTime must be after StartTime";
    public static final String VALIDATION_DATETIME_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    public static final String VALIDATION_TIME_REGEX = "[0-9]{4}";
    public static final String VALIDATION_CLASS_REGEX = VALIDATION_DATETIME_REGEX
            + " " + VALIDATION_TIME_REGEX + "-" + VALIDATION_TIME_REGEX;
    public static final String FLEXIBLE_CLASS_REGEX =
            "(?i)(Mon|Tue|Wed|Thu|Fri|Sat|Sun)[ ][0-9]{4}[-][0-9]{4}";

    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;

    public final String classDateTime; //User input eg. 2022-05-05 1200-1500

    /**
     * Constructs a {@code Class}.
     */
    public Class() {
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.classDateTime = "";
    }

    /**
     * Constructs a {@code Class}.
     *
     * @param date LocalDate object.
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     */
    public Class(LocalDate date, LocalTime startTime, LocalTime endTime, String classDateTime) {
        requireAllNonNull(date, startTime, endTime, classDateTime);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classDateTime = classDateTime;
    }

    /**
     * Returns a formatted date and time.
     *
     * @return String.
     */
    public String toString() {
        if (date == null) {
            // Tutor yet to edit in a class date.
            return "";
        }
        return convertToDateString(date) + " " + convertToTimeString(startTime, endTime);
    }

    /**
     * Returns a formatted time.
     *
     * @return String.
     */
    public String toTimeString() {
        if (date == null) {
            // Tutor yet to edit in a class date.
            return "";
        }
        return convertToTimeString(startTime, endTime);
    }

    /**
     * Returns a formatted date.
     *
     * @param date LocalDate object.
     * @return String.
     */
    private static String convertToDateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns a formatted time duration.
     *
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     * @return String.
     */
    private static String convertToTimeString(LocalTime startTime, LocalTime endTime) {
        int startHour = startTime.getHour();
        int startMin = startTime.getMinute();
        int endHour = endTime.getHour();
        int endMin = endTime.getMinute();
        return convertTime(startHour, startMin) + "-" + convertTime(endHour, endMin);
    }

    /**
     * Returns a formatted time.
     *
     * @param hour The number of hours in int.
     * @param min The number of minutes in int.
     * @return String.
     */
    private static String convertTime(int hour, int min) {
        String time = "";
        if (hour >= 12) {
            if (hour != 12) {
                hour -= 12;
            }
            if (min == 0) {
                time += hour + "PM";
            } else if (min < 10) {
                time += hour + ".0" + min + "PM";
            } else {
                time += hour + "." + min + "PM";
            }
        } else {
            if (min == 0) {
                time += hour + "AM";
            } else if (min < 10) {
                time += hour + ".0" + min + "AM";
            } else {
                time += hour + "." + min + "AM";
            }
        }
        return time;
    }

    /**
     * Validates whether classDatetime is valid.
     *
     * @param classDateTime String to be validated.
     * @return True if a given String object fits the format of 'yyyy-MM-dd 0000-2359'.
     */
    public static boolean isValidClassString(String classDateTime) {
        if (!classDateTime.matches(VALIDATION_CLASS_REGEX)) {
            return false;
        }
        String datetimeStr = classDateTime.substring(0, 10);
        String startTimeStr = classDateTime.substring(11, 15);
        String endTimeStr = classDateTime.substring(16);
        return isValidDateTimeString(datetimeStr) && isValidTimeString(startTimeStr) && isValidTimeString(endTimeStr);
    }

    /**
     * Validates whether classDatetime is valid.
     *
     * @param classDateTime String to be validated.
     * @return True if a given String object fits the format of 'yyyy-MM-dd 0000-2359'.
     */
    public static boolean isValidFlexibleClassString(String classDateTime) {
        if (!classDateTime.matches(FLEXIBLE_CLASS_REGEX)) {
            return false;
        }
        String startTimeStr = classDateTime.substring(4, 8);
        String endTimeStr = classDateTime.substring(9);
        return isValidTimeString(startTimeStr) && isValidTimeString(endTimeStr);
    }

    /**
     * Validates {@code date}.
     *
     * @param date String object.
     * @return True if is valid.
     */
    private static boolean isValidDateTimeString(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeException dateTimeException) {
            // text cannot be parsed
            return false;
        }
        return true;
    }

    /**
     * Validates {@code time}.
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Class // instanceof handles nulls
                && classDateTime.equals(((Class) other).classDateTime)); // state check
    }

}
