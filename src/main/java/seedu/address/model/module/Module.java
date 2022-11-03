package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Task's module code in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Module codes should be alphanumeric and should not be blank.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String moduleName;

    /**
     * Constructs a {@code Module}.
     *
     * @param module A valid module code.
     */
    public Module(String module) {
        requireNonNull(module);
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        this.moduleName = module;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModule(String test) {
        if (Objects.equals(test, "-")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return moduleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && moduleName.equalsIgnoreCase(((Module) other).moduleName)); // state check
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

}
