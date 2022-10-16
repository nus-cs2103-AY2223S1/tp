package seedu.address.model.reminder;

import static seedu.address.logic.parser.DateTimeParser.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;

/**
 * Represents a date and time.
 */
public class DateTime {
    private final String dateTime;

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidDateTime(String dateTime) {
        return true;
    }

    public String getDateTimeString() {
        return this.dateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.parse(DATE_TIME_FORMATTER);
    }
}
