package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

public class StudentID {

    public static final String MESSAGE_CONSTRAINTS = "Student ID should be in the format AXXXXXXXA";

    public static final String VALIDATION_REGEX = "^[A]\\d{7}[A-Z]$";

    public final String value;

    public StudentID(String studentID) {
        requireNonNull(studentID);
        checkArgument(isValidStudentID(studentID), MESSAGE_CONSTRAINTS);
        this.value = studentID;
    }

    public static boolean isValidStudentID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentID // instanceof handles nulls
                && value.equals(((StudentID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
