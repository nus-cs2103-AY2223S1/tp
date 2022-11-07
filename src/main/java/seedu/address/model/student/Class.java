package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Student's class date in the address book.
 * Guarantees: immutable;
 * is valid as declared in {@link #isValidClassString(String)}, {@link #isValidFlexibleClassString(String)},
 * {@link #isValidDuration(LocalTime, LocalTime)}.
 */
public class Class {

    public static final String MESSAGE_CONSTRAINTS = "Class can take any string"
            + " in either 'yyyy-MM-dd 0000-2359' or Day-of-Week 0000-2359 format"
            + "\nExamples:  2022-10-30 1000-1300,  Mon 1000-1300,  tue 1000-1300"
            + "\nDay-of-Week must be 3 letters and is case-insensitive";
    public static final String INVALID_DATE_ERROR_MESSAGE =
            "Date should be a valid date in the format of yyyy-MM-dd";
    public static final String INVALID_TIME_ERROR_MESSAGE =
            "Time should be in a valid time in the range of 0000 - 2359";
    public static final String INVALID_DATETIME_ERROR_MESSAGE =
            INVALID_DATE_ERROR_MESSAGE + ".\n" + INVALID_TIME_ERROR_MESSAGE;
    public static final String INVALID_DURATION_ERROR_MESSAGE = "End time must be after start time";
    public static final String INVALID_FULL_DAY_CLASS_ERROR_MESSAGE = "A class cannot be 24-hour long";
    public static final String VALIDATION_DATETIME_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    public static final String VALIDATION_TIME_REGEX = "[0-9]{4}";
    public static final String VALIDATION_STANDARD_CLASS_REGEX = VALIDATION_DATETIME_REGEX
            + " " + VALIDATION_TIME_REGEX + "-" + VALIDATION_TIME_REGEX;
    public static final String VALIDATION_FLEXIBLE_CLASS_REGEX =
            "(?i)(Mon|Tue|Wed|Thu|Fri|Sat|Sun) " + VALIDATION_TIME_REGEX + "-" + VALIDATION_TIME_REGEX;
    public static final String INVALID_FIND_COMMAND_MESSAGE =
            "Please include a date either in the format of yyyy-MM-dd or Day-of-Week"
            + "\nExamples: 2022-10-15, Mon, tue"
            + "\nDay-of-Week must be 3 letters and is case-insensitive";

    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;
    // yyyy-MM-dd 0000-2359 eg. 2022-05-05 1200-1500
    public final String classDateTime;

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
     * @param classDateTime String object which is a string representation of the date time.
     */
    public Class(LocalDate date, LocalTime startTime, LocalTime endTime, String classDateTime) {
        requireAllNonNull(date, startTime, endTime, classDateTime);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classDateTime = classDateTime;
    }

    /**
     * Overloaded constructor that generates the date in a String format to construct a {@code Class}
     *
     * @param date LocalDate object.
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     */
    public Class(LocalDate date, LocalTime startTime, LocalTime endTime) {
        requireAllNonNull(date, startTime, endTime);
        String stringOfDateTime = String.format("%s %s-%s", date.toString(),
                startTime.format(DateTimeFormatter.ofPattern("HHmm")),
                endTime.format(DateTimeFormatter.ofPattern("HHmm")));

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classDateTime = stringOfDateTime;
    }

    /**
     * Checks if class is empty.
     *
     * @return true if class is empty.
     */
    public boolean isEmpty() {
        return this.classDateTime.equals("");
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
     * Returns a formatted date and time for avail command.
     */
    public String toAvailCommandString() {
        return this.classDateTime;
    }

    /**
     * Returns a formatted time duration.
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
                time += hour;
            } else if (min < 10) {
                time += hour + ".0" + min;
            } else {
                time += hour + "." + min;
            }
            time += "PM";
        } else {
            if (hour == 0) {
                hour = 12;
            }
            if (min == 0) {
                time += hour;
            } else if (min < 10) {
                time += hour + ".0" + min;
            } else {
                time += hour + "." + min;
            }
            time += "AM";
        }
        return time;
    }

    /**
     * Returns true if {@code String classDateTime} is a valid input.
     *
     * @param classDateTime String to be validated.
     * @return true if a given string fits the format of 'yyyy-MM-dd 0000-2359'.
     */
    public static boolean isValidClassString(String classDateTime) {
        return isValidClassStringFormat(classDateTime) && isValidClassDateString(classDateTime);
    }

    /**
     * Returns true if {@code String classDateTime} is a valid input.
     *
     * @param classDateTime String to be validated.
     * @return true if format  {@code String VALIDATION_STANDARD_CLASS_REGEX}.
     */
    public static boolean isValidClassStringFormat(String classDateTime) {
        return classDateTime.matches(VALIDATION_STANDARD_CLASS_REGEX);
    }

    /**
     * Returns true if {@code String classDateTime} is a valid input.
     * Note that this should be always called when {@code String classDateTime} is of correct format
     * as specified by {@code isValidClassStringFormat}.
     *
     * @param classDateTime String to be validated.
     * @return true if it can be parsed to a valid date.
     */
    public static boolean isValidClassDateString(String classDateTime) {
        assert isValidClassStringFormat(classDateTime);

        String dateStr = classDateTime.substring(0, 10);
        String startTimeStr = classDateTime.substring(11, 15);
        String endTimeStr = classDateTime.substring(16);
        return isValidDateString(dateStr) && isValidTimeString(startTimeStr) && isValidTimeString(endTimeStr);
    }

    /**
     * Returns true if {@code String classDateTime} is a valid input.
     *
     * @param classDateTime String to be validated.
     * @return true if a given string fits the format of 'Day-of-Week 0000-2359'.
     */
    public static boolean isValidFlexibleClassString(String classDateTime) {
        if (!classDateTime.matches(VALIDATION_FLEXIBLE_CLASS_REGEX)) {
            return false;
        }
        String startTimeStr = classDateTime.substring(4, 8);
        String endTimeStr = classDateTime.substring(9);
        return isValidTimeString(startTimeStr) && isValidTimeString(endTimeStr);
    }

    /**
     * Returns true if {@code String date} is a valid date.
     *
     * @param date String to be validated.
     * @return true if is valid.
     */
    private static boolean isValidDateString(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeException dateTimeException) {
            // text cannot be parsed
            return false;
        }
        return true;
    }

    /**
     * Returns true if {@code String time} is a valid time.
     *
     * @param time String to be validated.
     * @return true if is valid.
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
     * Checks if both dates are the same.
     *
     * @param date to be checked against.
     * @return true if the dates are the same.
     */
    public boolean isSameDateAs(LocalDate date) {
        return this.date.equals(date);
    }

    /**
     * Returns true if {@code LocalTime startTime} and {@code LocalTime endTime} are valid.
     *
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     * @return true if startTime is before endTime.
     */
    public static boolean isValidDuration(LocalTime startTime, LocalTime endTime) {
        if (endTime.getHour() == 0 && endTime.getMinute() == 0) {
            // corner case where endTime is 12AM
            return !startTime.equals(endTime) || startTime.getHour() != 0;
        }
        return endTime.isAfter(startTime) && !endTime.equals(startTime);
    }

    /**
     * Returns true if class is a 24-hour class.
     *
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     * @return true if both startTime and endTime are 0000.
     */
    public static boolean isFullDayClass(LocalTime startTime, LocalTime endTime) {
        return endTime.getHour() == 0 && endTime.getMinute() == 0
                && startTime.getHour() == 0 && startTime.getMinute() == 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Class // instanceof handles nulls
                && classDateTime.equals(((Class) other).classDateTime)); // state check
    }

    /**
     * Returns compared result between {@code this} and the given {@code aClass} by Start Time in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     * {@code Class} and {@code aClass} must be non-null and on the same day;
     */
    public int compareToByStartTime(Class aClass) {
        requireAllNonNull(this.date, this.startTime, aClass.date, aClass.startTime);
        assert this.date.equals(aClass.date);
        return this.startTime.compareTo(aClass.startTime);
    }

    /**
     * Returns compared result between {@code this} and the given {@code aClass} by Class Time in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByClassTime(Class aClass) {
        requireAllNonNull(this.date, this.startTime, aClass.date, aClass.startTime);
        if (!this.date.equals(aClass.date)) {
            return this.date.compareTo(aClass.date);
        } else {
            return this.compareToByStartTime(aClass);
        }
    }
}
