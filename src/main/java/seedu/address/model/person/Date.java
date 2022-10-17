package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a date with a formatted String value
 * Guarantees: immutable;
 */
public class Date implements Comparable<Date> {
    private final LocalDate localDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param localDate A valid LocalDate.
     */
    public Date(LocalDate localDate) {
        requireNonNull(localDate);
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return localDate.format(java.time.format
                .DateTimeFormatter.ofPattern("d-MMM-yyyy"));
    }

    @Override
    public int compareTo(Date other) {
        return this.localDate.compareTo(other.localDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date// instanceof handles nulls
                && localDate.equals(((Date) other).localDate)); // state check
    }

    @Override
    public int hashCode() {
        return localDate.hashCode();
    }
}
