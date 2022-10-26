package seedu.intrack.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's salary in the internship tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary {


    public static final String MESSAGE_CONSTRAINTS = "Salaries should only contain numbers";
    public static final String VALIDATION_REGEX = "[0-9]+";
    public final String value;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        value = salary;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Salary // instanceof handles nulls
                && value.equals(((Salary) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
