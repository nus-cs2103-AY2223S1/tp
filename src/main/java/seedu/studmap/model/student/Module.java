package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's module in the student map.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Module must start with capital letters and contain "
            + "4 numbers after, with an optional character prefix.";

    public static final String VALIDATION_REGEX = "[A-Z]+\\d{4}[A-Z]?";

    public final String value;

    /**
     * Constructs a {@code Module}.
     *
     * @param module A valid module.
     */
    public Module(String module) {
        requireNonNull(module);
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        this.value = module;
    }

    /**
     * Returns true if a given string is a valid module.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Returns string used for sorting module.
     */
    public String toCmpString() {
        return this.value.isEmpty()
               ? null
               : value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && value.equals(((Module) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
