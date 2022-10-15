package seedu.nutrigoals.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents a Gender object
 */
public class Gender {
    public static final String VALIDATION_REGEX = "^[m,M,f,F]$";
    public static final String MESSAGE_CONSTRAINTS = "Gender must be a single char, either M or F";
    public static final String DEFAULT_VALUE = "M";
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    public final String value;

    /**
     * Initialises a gender object with the given parameters, and checks if its a valid gender
     * @param gender User's gender
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = gender.toUpperCase();
    }

    public Gender() {
        value = DEFAULT_VALUE;
    }

    /**
     * Checks if specified gender is valid
     * @param gender User's gender
     * @return true if specified string is either M, m, F or f
     */
    public static boolean isValidGender(String gender) {
        return (gender.matches(VALIDATION_REGEX));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Gender) {
            Gender other = (Gender) obj;
            return this.value.equals(other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        if (value.equals("M")) {
            return MALE;
        } else {
            return FEMALE;
        }
    }
}
