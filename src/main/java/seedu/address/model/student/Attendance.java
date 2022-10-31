package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's attendance in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAttendance(String)}
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only contain non-negative whole numbers";
    public static final String VALIDATION_REGEX = "\\d+";
    public final Integer value;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid attendance mark.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(attendance);
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidAttendance(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toString();
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
