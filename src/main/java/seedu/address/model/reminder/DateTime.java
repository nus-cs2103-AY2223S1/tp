package seedu.address.model.reminder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date and time.
 */
public class DateTime implements Comparable<DateTime> {
    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd 'at' HH:mm";
    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime} with the given dateTime string.
     * @param dateTime {@code String} representing a date and time.
     */
    public DateTime(String dateTime) {
        assert(isValidDateTimeString(dateTime));
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }

    /**
     * Constructs a {@code DateTime} with the given {@code LocalDateTime} object.
     * @param dateTime {@code LocalDateTime} representing a date and time.
     */
    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTimeString() {
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }

    @Override
    public int compareTo(DateTime other) {
        return this.getLocalDateTime().compareTo(other.getLocalDateTime());
    }

    public LocalDateTime getLocalDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && dateTime.equals(((DateTime) other).dateTime)); // state check
    }

    /**
     * Returns true if {@code dateTimeString} is a valid date time.
     */
    public static boolean isValidDateTimeString(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
            return true;
        } catch (DateTimeParseException pe) {
            return false;
        }
    }

    @Override
    public String toString() {
        return getDateTimeString();
    }
}
