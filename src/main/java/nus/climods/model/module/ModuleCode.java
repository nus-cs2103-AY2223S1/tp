package nus.climods.model.module;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's Code in the List of Modules. Guarantees: immutable
 */

public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Modules should be in the format example CS2103, and it should not be blank";

    /*
     * The first character of the module code must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String moduleCode;

    /**
     * Constructs a {@code Name}.
     *
     * @param moduleCode A valid moduleCode.
     */
    public ModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Returns true if a given string is a valid moduleCode.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && moduleCode.equals(((ModuleCode) other).moduleCode)); // state check
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }

}
