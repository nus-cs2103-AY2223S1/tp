package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's ID in the student map.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentID(String)}
 */
public class StudentID {

    public static final String MESSAGE_CONSTRAINTS = "Student ID should be in the format EXXXXXXX.";

    public static final String VALIDATION_REGEX = "^[E]\\d{7}";

    public final String value;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param studentID A valid name.
     */
    public StudentID(String studentID) {
        requireNonNull(studentID);
        //checkArgument(isValidStudentID(studentID), MESSAGE_CONSTRAINTS);
        this.value = studentID;
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
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
