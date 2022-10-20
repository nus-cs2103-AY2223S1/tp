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
    public static final String[] VALID_DATE_24HOUR_TIME_FORMATS = {
        "yy-M-d H:mm", "yy-MMM-d H:mm", "yyyy-M-d H:mm", "yyyy-MMM-d H:mm"};
    public static final String[] VALID_DATE_AMPM_TIME_FORMATS = {
        "yy-M-d h:mm a", "yy-MMM-d h:mm a", "yyyy-M-d h:mm a", "yyyy-MMM-d h:mm a",
        "yy-M-d h:mma", "yy-MMM-d h:mma", "yyyy-M-d h:mma", "yyyy-MMM-d h:mma"};
    public static final String[][] ALL_DATE_TIME_FORMATS = {
        VALID_DATE_24HOUR_TIME_FORMATS, VALID_DATE_AMPM_TIME_FORMATS };

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

        LocalDateTime dateTime = null;

        try {
            dateTime = LocalDateTime.parse(dateTimeString);
        } catch (DateTimeParseException e) {
            boolean isValidDateTime = false;
            for (int i = 0; i < ALL_DATE_TIME_FORMATS.length; i++) {
                dateTime = parseLocalDateTime(dateTimeString, ALL_DATE_TIME_FORMATS[i]);
                if (dateTime != null) {
                    isValidDateTime = true;
                    break;
                }
            }
            if (!isValidDateTime) {
                throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
            }
        }

        requireNonNull(dateTime);
        String parsedDateTimeString = dateTime.format(DateTimeFormatter.ofPattern(DateTime.DATE_TIME_FORMATTER));
        return new DateTime(parsedDateTimeString);
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
        return dateTime;
    }
}
