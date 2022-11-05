package seedu.taassist.model.session;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Session's date in TA-Assist.
 * Guarantees: immutable.
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be given in YYYY-MM-DD format and must be an actual date.";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMMM yyyy");

    private final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date {@code LocalDate} of the constructed {@code Date}.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        value = date;
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.format(DATE_TIME_FORMATTER);
    }

    @Override
    public int compareTo(Date other) {
        return value.compareTo(other.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
