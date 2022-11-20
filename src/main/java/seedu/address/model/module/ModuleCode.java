package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's code in the ProfNUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Codes should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullCode;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param code A valid code.
     */
    public ModuleCode(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        fullCode = code;
    }

    /**
     * Returns true if a given string is a valid code.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && fullCode.equals(((ModuleCode) other).fullCode)); // state check
    }

    @Override
    public int hashCode() {
        return fullCode.hashCode();
    }

}
