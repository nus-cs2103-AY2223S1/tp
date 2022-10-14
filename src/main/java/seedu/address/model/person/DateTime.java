package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a date and time with a formatted String value
 * Guarantees: immutable;
 */
public class DateTime {
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDateTime.toLocalDate();
    }

    @Override
    public String toString() {
        return localDateTime.format(java.time.format
                .DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm a"));
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
