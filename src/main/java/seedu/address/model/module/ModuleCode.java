package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's code in the address book.
 * Guarantees: immutable; is valid as decalred in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
        "Module Codes should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String moduleCode;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param moduleCodeInput A valid module code.
     */
    public ModuleCode(String moduleCodeInput) {
        requireNonNull(moduleCodeInput);
        checkArgument(isValidModuleCode(moduleCodeInput), MESSAGE_CONSTRAINTS);
        moduleCode = moduleCodeInput;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof ModuleCode
            && moduleCode.equals(((ModuleCode) other).moduleCode));
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }
}
