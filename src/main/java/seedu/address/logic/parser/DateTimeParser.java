package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reminder.DateTime;

/**
 * Contains utility methods used for parsing date time strings.
 */
public class DateTimeParser {
    public static final String[] ALL_DATE_TIME_FORMATS = {
        "yy-M-d H:mm", "yy-MMM-d H:mm", "yyyy-M-d H:mm", "yyyy-MMM-d H:mm",
        "yy-M-d h:mm a", "yy-MMM-d h:mm a", "yyyy-M-d h:mm a", "yyyy-MMM-d h:mm a",
        "yy-M-d h:mma", "yy-MMM-d h:mma", "yyyy-M-d h:mma", "yyyy-MMM-d h:mma"};

    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "Please enter a valid date time "
            + "in the following format: year-month-day hour:minute\n"
            + "Example: 2022-12-27 23:00, 22-08-25 07:30, 22-nov-11 7:00PM";

    /**
     * Parses a {@code String dateTime} into a {@code DateTime}.
     *
     * @throws ParseException if the given {@code String dateTime} is invalid.
     */
    public static DateTime parseDateTime(String dateTimeString) throws ParseException {
        requireNonNull(dateTimeString);

        LocalDateTime dateTime = parseLocalDateTime(dateTimeString, ALL_DATE_TIME_FORMATS);
        if (dateTime == null) {
            throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }

        requireNonNull(dateTime);
        return new DateTime(dateTime);
    }

    private static LocalDateTime parseLocalDateTime(String dateTimeString, String[] formats) {
        LocalDateTime dateTime = null;
        for (int i = 0; i < formats.length; i++) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(formats[i])
                    .toFormatter();
            try {
                dateTime = LocalDateTime.parse(dateTimeString, formatter);
                break;
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }

        if (dateTime == null) {
            try {
                dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }

        return dateTime;
    }
}
