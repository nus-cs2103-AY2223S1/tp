package seedu.address.model.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Returns true if the given birthdate is in the valid format.
     *
     * @param testDate Birthdate to be tested.
     * @return true when the given birthdate is valid.
     */
    public static boolean isValidDateFormat(String testDate) {
        try {
            LocalDate.parse(testDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if date is valid.
     *
     * @param testDate Date to be tested.
     * @return True if valid.
     */
    public static boolean isValidDateTimeFormat(String testDate) {
        try {
            LocalDateTime.parse(testDate, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the given birthdate is not after the current date.
     *
     * @param testDate Birthdate to be tested.
     * @return true when the given birthdate is not after the current date.
     */
    public static boolean isNotFutureDate(String testDate) {
        LocalDate date = LocalDate.parse(testDate, DATE_FORMAT);
        LocalDate currDate = LocalDate.now();
        return !date.isAfter(currDate);
    }
}
