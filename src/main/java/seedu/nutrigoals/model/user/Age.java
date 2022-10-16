package seedu.nutrigoals.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents the user's age
 */
public class Age {
    public static final String VALIDATION_REGEX = "^[1-9]\\d*$";
    public static final String MESSAGE_CONSTRAINTS = "Age must be a number greater than 0";
    public static final String DEFAULT_VALUE = "0";
    public final String value;

    /**
     * Initialises an age object with the given parameters, and checks if it's a valid age
     * @param age
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        value = age;
    }

    public Age() {
        value = DEFAULT_VALUE;
    }

    public static boolean isValidAge(String age) {
        return age.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Age)) {
            return false;
        }

        Age other = (Age) obj;
        return value.equals(other.value);
    }
}
