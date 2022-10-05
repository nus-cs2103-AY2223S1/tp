package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's attendance in the SETA application.
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String attendance;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid attendance number.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        this.attendance = attendance;
    }

    /**
     * Returns true if a given string is a valid attendance number.
     */
    public static boolean isValidAttendance(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return attendance;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && attendance.equals(((Attendance) other).attendance)); // state check
    }

    @Override
    public int hashCode() {
        return attendance.hashCode();
    }
}
