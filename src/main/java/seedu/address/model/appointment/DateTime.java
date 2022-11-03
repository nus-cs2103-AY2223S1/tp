package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.DateTimeParser.DATE_TIME_FORMAT;

import java.time.LocalDateTime;

/**
 * Represents a date and time with a formatted String value
 * Guarantees: immutable;
 */
public class DateTime implements Comparable<DateTime> {
    public static final String MESSAGE_CONSTRAINTS = "Invalid date time format";
    private final LocalDateTime localDateTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param localDateTime A valid LocalDateTime.
     */
    public DateTime(LocalDateTime localDateTime) {
        requireNonNull(localDateTime);
        this.localDateTime = localDateTime;
    }
    public Date getDate() {
        return new Date(localDateTime.toLocalDate());
    }

    public Time getTime() {
        return new Time(localDateTime.toLocalTime());
    }

    public String getTimeFormat() {
        return localDateTime.format(java.time.format
                .DateTimeFormatter.ofPattern("hh:mm a"));
    }


    public int getDay() {
        return this.localDateTime.getDayOfMonth();
    }

    public int getMonth() {
        return this.localDateTime.getMonthValue();
    }

    public int getYear() {
        return this.localDateTime.getYear();
    }

    @Override
    public String toString() {
        return localDateTime.format(java.time.format
                .DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public int compareTo(DateTime other) {
        return this.localDateTime.compareTo(other.localDateTime);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime// instanceof handles nulls
                && localDateTime.toString().equals(((DateTime) other).localDateTime.toString())); // state check
    }

    @Override
    public int hashCode() {
        return localDateTime.hashCode();
    }
}
