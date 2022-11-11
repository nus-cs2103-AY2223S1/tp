package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's ID in the student map.
 * Guarantees: immutable
 */
public class StudentID {

    public static final String MESSAGE_CONSTRAINTS = "StudentID cannot be empty!";

    public final String value;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param studentID A valid name.
     */
    public StudentID(String studentID) {
        requireNonNull(studentID);
        checkArgument(isValidStudentID(studentID), MESSAGE_CONSTRAINTS);
        this.value = studentID;
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
    public static boolean isValidStudentID(String studentID) {
        return studentID != null && !studentID.isEmpty();
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Returns string used for sorting studentID
     */
    public String toCmpString() {
        return this.value.isEmpty()
                ? null
                : value;
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
