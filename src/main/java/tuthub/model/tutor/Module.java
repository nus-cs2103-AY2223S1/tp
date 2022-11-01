package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutor's module in tuthub.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Module should only contain alphanumeric"
        + " characters with no spaces, and it should not be blank";

    /*
     * Covers all module code variants in NUS.
     */
    public static final String VALIDATION_REGEX = "[A-Z]{2}[A-Z]?[A-Z]?\\d{4}[A-Z]?[A-Z]?";

    public final String value;

    /**
     * Constructs an {@code Module}.
     *
     * @param module A valid module.
     */
    public Module(String module) {
        requireNonNull(module);
        module = module.toUpperCase(); // easier regex checking and storing
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        value = module;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return '[' + value + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Module // instanceof handles nulls
            && value.equals(((Module) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
