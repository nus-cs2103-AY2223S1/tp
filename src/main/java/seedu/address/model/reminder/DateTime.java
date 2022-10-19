package seedu.address.model.reminder;

import static seedu.address.logic.parser.DateTimeParser.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date and time.
 */
public class DateTime implements Comparable<DateTime> {
    private final String dateTime;

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTimeString() {
        return this.dateTime;
    }

    @Override
    public int compareTo(DateTime other) {
        return this.getLocalDateTime().compareTo(other.getLocalDateTime());
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return dateTime;
    }
}
