package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's module code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}.
 */
public class ModuleCode implements Comparable<ModuleCode> {
    public static final String MESSAGE_CONSTRAINTS =
            "Module code should only contain alphanumeric characters, and it should not be blank";

    // The module code is alphanumeric and cannot include spaces.
    public static final String VALIDATION_REGEX = "[a-z|A-Z|0-9]{1,}";

    public final String value;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param value A valid moduleCode.
     */
    public ModuleCode(String value) {
        requireNonNull(value);
        value = value.trim();
        checkArgument(isValidModuleCode(value), MESSAGE_CONSTRAINTS);
        this.value = value.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid moduleCode.
     */
    public static boolean isValidModuleCode(String test) {
        if (test == null) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the module code as a {@code String} in upper case.
     */
    public String getModuleCodeAsUpperCaseString() {
        return value.toUpperCase();
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && value.equalsIgnoreCase(((ModuleCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(ModuleCode other) {
        return value.compareTo(other.value);
    }
}
