package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline implements Comparable<Deadline> {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be of the form YYYY-MM-DD, eg 2022-10-07";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    public final String date;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param date A valid deadline.
     */
    public Deadline(String date) {
        requireNonNull(date);
        checkArgument(isValidDeadline(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && date.equals(((Deadline) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this date is before,
     * equal to, or after the compared deadline.
     */
    @Override
    public int compareTo(Deadline o) {
        return this.date.compareTo(o.date);
    }
}
