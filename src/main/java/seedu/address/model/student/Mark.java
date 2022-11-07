package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's attendance for the current day.
 */
public class Mark {
    public static final String MESSAGE_CONSTRAINTS = "Mark can take in a boolean value,"
            + " and its default value is false";

    private Boolean isPresent;

    /**
     * Constructs {@code Mark} to track attendance.
     *
     * @param isPresent the attendance of the student.
     */
    public Mark(Boolean isPresent) {
        requireNonNull(isPresent);
        checkArgument(isValidAttendance(isPresent), MESSAGE_CONSTRAINTS);
        this.isPresent = isPresent;
    }

    /**
     * Constructs {@code Mark} with default attendance as false.
     */
    public Mark() {
        this.isPresent = Boolean.FALSE;
    }

    /**
     * Validates whether attendance is a valid boolean.
     *
     * @param subjectToTest the attendance to be validated.
     * @return true if a given attendance is a boolean.
     */
    public static boolean isValidAttendance(Boolean subjectToTest) {
        if (!(subjectToTest == Boolean.FALSE || subjectToTest == Boolean.TRUE)) {
            return false;
        }
        return true;
    }

    /**
     * Getter function to access the attendance status.
     *
     * @return true if attendance is marked.
     */
    public Boolean isMarked() {
        return isPresent;
    }

    @Override
    public String toString() {
        return isPresent.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mark // instanceof handles nulls
                && isPresent.equals(((Mark) other).isPresent)); // state check
    }

    @Override
    public int hashCode() {
        return isPresent.hashCode();
    }

}
