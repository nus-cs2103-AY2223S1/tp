package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reminder.DateTime;

/**
 * Contains utility methods used for parsing date time strings.
 */
public class DateTimeParser {
    public static final String DATE_TIME_FORMATTER = "dd MMM yy 'at' HH:mm";
    public static final String[] VALID_ISO8601_DATE_TIME_FORMATS = {
        "yyyy-MM-dd", "yyyy-MM-dd H:mm", "yyyy-MM-dd h:mm a", "yyyy-MM-dd h:mma"};
    public static final String[] VALID_DATE_FORMATS = {
        "d MM yy", "d/MM/yy", "d-MM-yy", "d MMM yy",
        "d/MMM/yy", "d-MMM-yy", "d MM yyyy", "d/MM/yyyy",
        "d-MM-yyyy", "d MMM yyyy", "d/MMM/yyyy", "d-MMM-yyyy"};
    public static final String[] VALID_DATE_24HOUR_TIME_FORMATS = {
        "d MM yy H:mm", "d/MM/yy H:mm", "d-MM-yy H:mm", "d MMM yy H:mm",
        "d/MMM/yy H:mm", "d-MMM-yy H:mm", "d MM yyyy H:mm", "d/MM/yyyy H:mm",
        "d-MM-yyyy H:mm", "d MMM yyyy H:mm", "d/MMM/yyyy H:mm", "d-MMM-yyyy H:mm"};
    public static final String[] VALID_DATE_AMPM_TIME_FORMATS = {
        "d MM yy h:mm a", "d/MM/yy h:mm a", "d-MM-yy h:mm a", "d MMM yy h:mm a",
        "d/MMM/yy h:mm a", "d-MMM-yy h:mm a", "d MM yyyy h:mm a", "d/MM/yyyy h:mm a",
        "d-MM-yyyy h:mm a", "d MMM yyyy h:mm a", "d/MMM/yyyy h:mm a", "d-MMM-yyyy h:mm a"};
    public static final String[][] ALL_DATE_TIME_FORMATS = {VALID_ISO8601_DATE_TIME_FORMATS, VALID_DATE_FORMATS,
        VALID_DATE_24HOUR_TIME_FORMATS, VALID_DATE_AMPM_TIME_FORMATS};

    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "Please enter a valid date time";

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
        String parsedDateTimeString = dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
        return new DateTime(parsedDateTimeString);
    }

    private static LocalDateTime parseLocalDateTime(String dateTimeString, String[] formats) {
        LocalDateTime dateTime = null;
        for (int i = 0; i < formats.length; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formats[i]);
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
