package seedu.nutrigoals.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents the user's age
 */
public class Age {
    public static final String VALIDATION_REGEX = "\\d+";
    public static final String MESSAGE_CONSTRAINTS = "Age must take on a positive integer than is not too large.";
    public static final String DEFAULT_VALUE = "0";
    public static final int MAX_AGE = 100;
    public static final int MIN_AGE = 0;
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

    /**
     * Checks if the {@code age} is valid
     * @param age Input age
     * @return true if the {@code age} is valid
     */
    public static boolean isValidAge(String age) {
        if (!age.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue > MIN_AGE && ageValue < MAX_AGE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getAge() {
        return Integer.parseInt(value);
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

    @Override
    public String toString() {
        return value;
    }
}
