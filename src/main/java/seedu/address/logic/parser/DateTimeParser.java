package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses string representation of a date and time and creates
 * a new LocalDateTime object
 */
public class DateTimeParser {
    private static final String DATE_TIME_FORMAT = "d-MMM-yyyy hh:mm a";
    private static final DateTimeFormatter dateTimeFormatter = java.time.format
            .DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * Parses the given {@code String} representing a date and time
     * and returns a LocalDateTime object.
     *
     * @param str String representing the LocalDateTime to be returned.
     * @return LocalDateTime parsed from the input String.
     */
    public LocalDateTime getLocalDateTimeFromString(String str) {
        return LocalDateTime.parse(str, DateTimeParser.dateTimeFormatter);
    }
}
