package seedu.address.model.person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Person's class date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassString(String)}
 */
public class Class {

    public static final String MESSAGE_CONSTRAINTS = "Class can take any string"
            + " in the format of 'yyyy-MM-dd 0000-2359'";
    public static final String INVALID_DATETIME_ERROR_MESSAGE = "Date should be a valid date";
    public static final String INVALID_TIME_ERROR_MESSAGE = "Time should be in the range of 0000 - 2359";
    public static final String INVALID_DURATION_ERROR_MESSAGE = "EndTime must be after StartTime";
    public static final String VALIDATION_DATETIME_REGEX = "[0-9]{4}[-][0-9]{2}[-][0-9]{2}";
    public static final String VALIDATION_TIME_REGEX = "[0-9]{4}";
    public static final String VALIDATION_CLASS_REGEX = VALIDATION_DATETIME_REGEX
            + "[ ]" + VALIDATION_TIME_REGEX + "[-]" + VALIDATION_TIME_REGEX;

    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;

    public final String classDateTime; //User input eg. 2022-05-05 1200-1500
    public final String classToString; //formatted date eg. 5 May 2022 12PM-3PM

    /**
     * Constructs a {@code Class}.
     *
     * @param classDateTime Defaulted to be "".
     */
    public Class(String classDateTime) {
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.classDateTime = "";
        this.classToString = "";
    }

    /**
     * Constructs a {@code Class}.
     *
     * @param date LocalDate object.
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     */
    public Class(LocalDate date, LocalTime startTime, LocalTime endTime, String classDateTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classToString = convertToString(date, startTime, endTime);
        this.classDateTime = classDateTime;
    }

    /**
     * Returns a formatted date and time.
     *
     * @param date LocalDate object.
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     * @return String.
     */
    public static String convertToString(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return convertToDateString(date) + " " + convertToTimeString(startTime, endTime);
    }

    /**
     * Returns a formatted date.
     *
     * @param date LocalDate object.
     * @return String.
     */
    public static String convertToDateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns a formatted time.
     *
     * @param startTime LocalTime object.
     * @param endTime LocalTime object.
     * @return String.
     */
    public static String convertToTimeString(LocalTime startTime, LocalTime endTime) {
        String time = "";
        int startHour = startTime.getHour();
        int startMin = startTime.getMinute();
        int endHour = endTime.getHour();
        int endMin = endTime.getMinute();
        if (startHour >= 12) {
            if (startHour != 12) {
                startHour -= 12;
            }
            if (startMin == 0) {
                time += startHour + "PM";
            } else if (startMin < 10) {
                //Pad with zero
                time += startHour + ".0" + startMin + "PM";
            } else {
                time += startHour + "." + startMin + "PM";
            }
        } else {
            if (startMin == 0) {
                time += startHour + "AM";
            } else if (startMin < 10) {
                //Pad with zero
                time += startHour + ".0" + startMin + "AM";
            } else {
                time += startHour + "." + startMin + "AM";
            }
        }
        time += "-";
        if (endHour >= 12) {
            if (endHour != 12) {
                endHour -= 12;
            }
            if (endMin == 0) {
                time += endHour + "PM";
            } else if (endMin < 10) {
                //Pad with zero
                time += endHour + ".0" + endMin + "PM";
            } else {
                time += endHour + "." + endMin + "PM";
            }
        } else {
            if (endMin == 0) {
                time += endHour + "AM";
            } else if (endMin < 10) {
                //Pad with zero
                time += endHour + ".0" + endMin + "AM";
            } else {
                time += endHour + "." + endMin + "AM";
            }
        }
        return time;
    }

    /**
     * Validates whether classDatetime is valid.
     *
     * @param classDatetime String to be validated.
     * @return True if a given String object fits the format of 'yyyy-MM-dd 0000-2359'.
     */
    public static boolean isValidClassString(String classDatetime) {
        if (!classDatetime.matches(VALIDATION_CLASS_REGEX)) {
            return false;
        }

        String datetimeStr = classDatetime.substring(0, 10);
        String startTimeStr = classDatetime.substring(11, 15);
        String endTimeStr = classDatetime.substring(16);

        return isValidDatetimeString(datetimeStr) && isValidTimeString(startTimeStr) && isValidTimeString(endTimeStr);
    }

    /**
     * Helper method to validate {@code date}.
     */
    private static boolean isValidDatetimeString(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeException de) {
            // text cannot be parsed
            return false;
        }
        return true;
    }

    /**
     * Helper method to validate {@code time}.
     */
    private static boolean isValidTimeString(String time) {
        Integer hour = Integer.valueOf(time.substring(0, 2));
        Integer minute = Integer.valueOf(time.substring(2));
        try {
            LocalTime.of(hour, minute);
        } catch (DateTimeException de) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Class // instanceof handles nulls
                && classToString.equals(((Class) other).classToString)); // state check
    }

}
