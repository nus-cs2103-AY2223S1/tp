package seedu.modquik.model.student;
import static java.util.Objects.requireNonNull;
import static seedu.modquik.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's grade in ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should be A, B, C, D or F. If grade is unavailable, leave as PENDING...";
    public static final String VALIDATION_REGEX = "^[a-dA-DfF]$";
    public final String value;

    /**
     * Constructs a {@code Grade}.
     *
     * @param grade A valid grade.
     */
    public Grade(String grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        value = grade;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("PENDING...");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && value.equals(((Grade) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
