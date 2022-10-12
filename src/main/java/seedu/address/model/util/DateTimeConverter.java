package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class to provide static methods to convert string
 * into DateTime formats for manipulation
 */
public class DateTimeConverter {
    /**
     * Function to convert string values to LocalDateTime values
     * which are primarily used for comparisons
     * specifically, converts strings in the dd-MM-yyyy HHmm format into LocalDateTime
     * @param date string to be converted
     * @return LocalDateTime value of the string
     * @throws DateTimeParseException if it fails to parse IE it's in the wrong format
     */
    public static LocalDateTime stringToLocalDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(date, format);
    }
    /**
     * Function to convert processed string values to LocalDateTime values
     * which are primarily used for comparisons
     * specifically, converts strings in the EEEE, dd MMMM yyyy HH:mm a format into LocalDateTime
     * @param date string to be converted
     * @return LocalDateTime value of the string
     * @throws DateTimeParseException if it fails to parse IE it's in the wrong format
     */
    public static LocalDateTime processedStringToLocalDatetime(String date) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm a");
        return LocalDateTime.parse(date, format);
    }
}
