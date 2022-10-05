package seedu.address.model.person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's class date in the address book.
 * Guarantees: immutable.
 */
public class Class {
    public static final String MESSAGE_CONSTRAINTS = "Class can take any string"
            + " in the format of 'yyyy-MM-dd 0000-2300'";
    public static final String INVALID_DATETIME_ERROR_MESSAGE = "Date should be a valid date";
    public static final String INVALID_TIME_ERROR_MESSAGE = "Time should be in the range of 0000 - 2359";
    public static final String VALIDATION_DATETIME_REGEX = "[1-9][0-9]{3}[-][01][0-9][-][0123][0-9]";
    public static final String VALIDATION_TIME_REGEX = "[012][0-9][0-5][0-9]";
    public static final String VALIDATION_CLASS_REGEX = VALIDATION_DATETIME_REGEX
            + "[ ]" + VALIDATION_TIME_REGEX + "[-]" + VALIDATION_TIME_REGEX;
    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;


    /**
     * Constructs a {@code Class}.
     *
     * @param date
     * @param startTime
     * @param endTime
     */
    public Class(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Validates whether classDatetime is valid.
     * @param classDatetime the string to be validated.
     * @return true if a given string fits the format of 'yyyy-MM-d 0000-2300'
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
     * Helper method to validate {@code date}
     */
    private static boolean isValidDatetimeString(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException de) {
            // text cannot be parsed
            return false;
        }
        return true;
    }

    /**
     * Helper method to validate {@code time}
     */
    private static boolean isValidTimeString(String time) {
        Integer hour = Integer.valueOf(time.substring(0,2));
        Integer minute = Integer.valueOf(time.substring(2));
        try {
            LocalTime.of(hour, minute);
        } catch (DateTimeException de) {
            return false;
        }
        return true;
    }
}
