package seedu.address.model.commission;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Commission's name in the address book.
 * Guarantees: immutable;
 */
public class Deadline {

    public final LocalDate deadline;

    /**
     * Initialises a deadline.
     * @param deadline Date of the deadline.
     */
    public Deadline(LocalDate deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return deadline.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Deadline)
                && deadline.equals(((Deadline) other).deadline);
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

}
