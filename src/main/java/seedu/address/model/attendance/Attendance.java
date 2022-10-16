package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * * Represents a Person's attendance in the address book.
 */
public class Attendance {
    public static final String VALIDATION_REGEX = "[0-1]";
    public static final String MESSAGE_CONSTRAINTS = "attendance is either a 0(absent) or 1(present)";
    public final String value;

    /**
     * Constructs an {@code Email}.
     * @param attendance A valid attendance.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidMark(attendance), MESSAGE_CONSTRAINTS);
        value = attendance;
    }

    /**
     * Constructs an empty {@code Attendance}.
     */
    public Attendance() {
        value = "0";
    }

    /**
     * Returns if a given string is a valid attendance
     */
    public static boolean isValidMark(String test) {
        return test.matches(VALIDATION_REGEX);
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
