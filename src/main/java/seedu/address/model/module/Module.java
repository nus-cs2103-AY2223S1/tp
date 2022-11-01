package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleName(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Module names should contain a 2-4 letter prefix, followed by a "
            + "4 digit module code, and an optional letter suffix";
    public static final String VALIDATION_REGEX = "^[a-zA-Z]{2,4}[0-9]{4}[a-zA-Z]{0,1}$";

    public final String moduleName;

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleName A valid module name.
     */
    public Module(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && moduleName.equals(((Module) other).moduleName)); // state check
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + moduleName + ']';
    }
}
