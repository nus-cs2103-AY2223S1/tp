package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutor's ID in Tuthub.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Student IDs should start with A, followed by "
            + "7 numbers, and end with any capital letter.";

    /*
     * The first character of studentIds must be A, the next 7 values can be any
     * integer. Finally, the last value can be any capital letter.
     */
    public static final String VALIDATION_REGEX = "(^A)\\d{7}[A-Z]$";

    public final String value;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param studentId A valid Student ID.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
