package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * class to provide static methods to convert string
 * into DateTime formats for manipulation
 */
public class DateTimeConverter {
    private static final Logger logger = LogsCenter.getLogger(DateTimeConverter.class);

    /**
     * Function to convert string values to LocalDateTime values
     * which are primarily used for comparisons
     * specifically, converts strings in the dd-MM-uuuu HHmm format into LocalDateTime
     * note that uuuu refers to proleptic-year whereas yyyy refers to yearofera(AD/BC) when ResolverStyle.Strict is used
     * @param date string to be converted
     * @return LocalDateTime value of the string
     * @throws DateTimeParseException if it fails to parse IE it's in the wrong format
     */
    public static LocalDateTime stringToLocalDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
        return LocalDateTime.parse(date, format);
    }
    /**
     * Function to convert processed string values to LocalDateTime values
     * which are primarily used for comparisons
     * specifically, converts strings in the EEEE, dd MMMM uuuu hh:mm a format into LocalDateTime
     * note that uuuu refers to proleptic-year whereas yyyy refers to yearofera(AD/BC) when ResolverStyle.Strict is used
     * @param date string to be converted
     * @return LocalDateTime value of the string
     * @throws DateTimeParseException if it fails to parse IE it's in the wrong format
     */
    public static LocalDateTime processedStringToLocalDatetime(String date) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, d MMMM uuuu hh:mm a")
            .withResolverStyle(ResolverStyle.STRICT);
        return LocalDateTime.parse(date, format);
    }

    /**
     * Function to convert processed string values to dd-MM-uuuu format
     * Used for storage
     * note that uuuu refers to proleptic-year whereas yyyy refers to yearofera(AD/BC) when ResolverStyle.Strict is used
     * @param date string to be converted
     * @return String in dd-mm-yyyy format
     * @throws DateTimeParseException if it fails to parse IE it's in the wrong format
     */
    public static String processFullDateToLocalDatetime(String date) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, d MMMM uuuu hh:mm a")
            .withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
        return LocalDateTime.parse(date, format).format(newPattern);
    }

}
