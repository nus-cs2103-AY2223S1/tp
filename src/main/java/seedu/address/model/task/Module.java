package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module in the task tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS =
            "Modules should only contain alphanumeric characters with no spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}]+";

    public final String fullName;

    /**
     * Constructs a {@code Module}.
     *
     * @param name A valid module name.
     */
    public Module(String name) {
        requireNonNull(name);
        checkArgument(isValidModule(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && fullName.equals(((Module) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
