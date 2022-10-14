package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's GradeProgress in the address book.
 * Guarantees: immutable; is valid
 */
public class GradeProgress {
    public final String value;

    /**
     * Constructs an {@code GradeBook}.
     *
     * @param gradeProgress A description of the homework.
     */
    public GradeProgress(String gradeProgress) {
        requireNonNull(gradeProgress);
        value = gradeProgress;
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
