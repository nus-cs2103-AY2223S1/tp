package seedu.address.model.student;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should be A, B, C, D or F";
    public static final String VALIDATION_REGEX = "^[a-dA-DfF]$";
    public final String value;

    /**
     * Constructs a {@code Grade}.
     *
     * @param grade A valid grade.
     */
    public Grade(String grade) {
        if (!Objects.equals(grade, "")) {
            checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
            value = grade.toUpperCase();
        } else {
            value = "PENDING...";
        }
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
