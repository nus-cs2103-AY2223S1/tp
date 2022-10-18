package jarvis.model;

import static jarvis.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a student's matriculation number.
 */
public class MatricNum {
    public static final String MESSAGE_CONSTRAINTS = "Matriculation Numbers must start with 'A', "
            + "followed by 7 digits and a capital letter";
    public static final String VALIDATION_REGEX = "A[0-9]{7}[A-Z]";

    public final String value;

    /**
     * Creates a MatricNum.
     * @param matricNum String representing a valid matriculation number.
     */
    public MatricNum(String matricNum) {
        requireNonNull(matricNum);
        checkArgument(isValidMatricNum(matricNum), MESSAGE_CONSTRAINTS);
        value = matricNum;
    }

    public static boolean isValidMatricNum(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatricNum // instanceof handles nulls
                && value.equals(((MatricNum) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
