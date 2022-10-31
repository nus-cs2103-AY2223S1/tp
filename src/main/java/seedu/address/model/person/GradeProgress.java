package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Grade Progress in the address book.
 * Guarantees: immutable; is valid
 */
public class GradeProgress {
    public static final String MESSAGE_CONSTRAINTS = "Grade progress should not be blank";
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    public final String value;

    /**
     * Constructs an {@code GradeBook}.
     *
     * @param gradeProgress A description of the homework.
     */
    public GradeProgress(String gradeProgress) {
        requireNonNull(gradeProgress);
        checkArgument(isValidGradeProgress(gradeProgress), MESSAGE_CONSTRAINTS);
        value = gradeProgress;
    }

    /**
     * Returns true if a given string is a valid grade progress.
     */
    public static boolean isValidGradeProgress(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeProgress // instanceof handles nulls
                && value.equalsIgnoreCase(((GradeProgress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
