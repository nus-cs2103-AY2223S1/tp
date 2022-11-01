package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module code in ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Module's code should consists of a two- or three-letter prefix, four digits"
                + " and optionally a one-letter suffix";

    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,3}[\\d]{4}[a-zA-Z]?";

    public final String moduleCode;

    /**
     * Constructs a {@code Venue}.
     *
     * @param moduleCode A valid module code.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModule(moduleCode), MESSAGE_CONSTRAINTS);
        this.moduleCode = moduleCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModule(String test) {
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
