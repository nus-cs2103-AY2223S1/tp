package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's student ID in the FYP manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "ID should be of the format 'A' + (7 digits) + (1 letter) , and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";

    public final String id;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid studentId.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        id = studentId;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && id.equals(((StudentId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
