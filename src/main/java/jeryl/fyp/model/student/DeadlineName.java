package jeryl.fyp.model.student;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.util.AppUtil.checkArgument;

/**
 * Represents a Deadline's deadline name.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadlineName(String)}
 */
public class DeadlineName {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullDeadlineName;

    /**
     * Constructs a {@code DeadlineName}.
     *
     * @param deadlineName A valid ddl name.
     */
    public DeadlineName(String deadlineName) {
        requireNonNull(deadlineName);
        checkArgument(isValidDeadlineName(deadlineName), MESSAGE_CONSTRAINTS);
        fullDeadlineName = deadlineName;
    }

    /**
     * Returns true if a given string is a valid deadline name.
     */
    public static boolean isValidDeadlineName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullDeadlineName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineName // instanceof handles nulls
                && fullDeadlineName.equals(((DeadlineName) other).fullDeadlineName)); // state check
    }

    @Override
    public int hashCode() {
        return fullDeadlineName.hashCode();
    }

}
