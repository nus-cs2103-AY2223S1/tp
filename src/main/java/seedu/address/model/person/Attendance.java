package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only contain a current attendance number and a total maximum attendance number";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String attendance;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid phone number.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        this.attendance = attendance;
    }

    /**
     * Returns true if a given string is a valid phone number.
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
                || (other instanceof Attendance// instanceof handles nulls
                && attendance.equals(((Attendance) other).attendance)); // state check
    }

    @Override
    public int hashCode() {
        return attendance.hashCode();
    }

}
