package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

/**
 * * Represents a Person's attendance in the address book.
 *  * Guarantees: immutable; is always valid
 */
public class Attendance {
    public final String value;

    public Attendance(String attendance) {
        requireNonNull(attendance);
        value = attendance;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && value.equals(((Attendance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
