package seedu.address.logic.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

/**
 * Represents a parser that parses String to LocalDateTime in various patterns.
 */
public class DateTimeParser {
    // only allow format of yyyy-MM-dd and yyyy-MM-dd HH:mm as of now
    // if time is not specified, deadline is set to be 23:59, at the end of the day
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("uuuu-MM-dd")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns LocalDateTime object of the specified date and time.
     * Only accepts string of format "yyyy-MM-dd" or "yyyy-MM-dd HH:mm".
     *
     * @param dateStr String containing the date and time.
     * @return LocalDateTime object
     * @throws DateTimeException if invalid string format given.
     */
    public static LocalDateTime getDateTime(String dateStr) throws DateTimeException {
        return LocalDateTime.parse(dateStr, FORMATTER);
    }

    /**
     * Returns a String representation of LocalDateTime object.
     *
     * @param dateTime LocalDateTime object of date and time.
     * @return String representation of date and time.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);
    }

}
