package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

/**
 * Represents a date and time with a formatted String value
 * Guarantees: immutable;
 */
public class DateTime implements Comparable<DateTime> {
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

    @Override
    public String toString() {
        return localDateTime.format(java.time.format
                .DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm a"));
    }

    @Override
    public int compareTo(DateTime other) {
        return this.localDateTime.compareTo(other.localDateTime);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime// instanceof handles nulls
                && localDateTime.equals(((DateTime) other).localDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return localDateTime.hashCode();
    }
}