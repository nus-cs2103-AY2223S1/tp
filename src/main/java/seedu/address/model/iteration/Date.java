package seedu.address.model.iteration;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents the date tied to an Iteration in the address book.
 * Guarantees: immutable;
 */
public class Date {

    private final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid non-null date.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return otherDate.date.equals(date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
