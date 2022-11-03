package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Parses string representation of a date and time and creates
 * a new LocalDateTime object
 */
public class DateTimeParser {
    public static final String DATE_TIME_FORMAT = "d-MM-uuuu HH:mm";

    private static final DateTimeFormatter dateTimeFormatter = java.time.format
            .DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).withResolverStyle(ResolverStyle.STRICT);


    /**
     * Parses the given {@code String} representing a date and time
     * and returns a LocalDateTime object.
     *
     * @param str String representing the LocalDateTime to be returned.
     * @return LocalDateTime parsed from the input String.
     */
    public static LocalDateTime parseLocalDateTimeFromString(String str) {
        return LocalDateTime.parse(str, DateTimeParser.dateTimeFormatter);
    }

    /**
     * Checks whether the input String has the correct DateTime format
     *
     * @param str the String representing the LocalDateTime to be parsed.
     * @return boolean value describing whether the input String has
     *         the correct DateTime format.
     */
    public static boolean isValidDateTime(String str) {
        try {
            parseLocalDateTimeFromString(str);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }
}
