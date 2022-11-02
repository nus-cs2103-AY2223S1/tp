package modtrekt.model.module;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's code in the module list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCode(String)}
 */
public class ModCode implements Comparable<ModCode> {

    public static final String MESSAGE_CONSTRAINTS =
            "Code should only contain alphanumeric characters, should not contain white space and should be between 6"
                    + " and 9 characters long";
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "^[a-zA-Z0-9]{6,9}$";
    public static final String VALIDATION_REGEX = ALPHANUMERIC_NO_UNDERSCORE;

    private final String value;

    /**
     * Constructs an {@code ModCode}.
     *
     * @param code A valid email address.
     */
    public ModCode(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        value = code.toUpperCase();
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModCode // instanceof handles nulls
                && value.equals(((ModCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(ModCode that) {
        return this.value.compareTo(that.value);
    }
}
