package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's module code in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS =
            "Modules should contain only alphanumeric characters and should not be blank.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\S]+";

    public final String module;

    /**
     * Constructs a {@code Module}.
     *
     * @param module A valid  code.
     */
    public Module(String module) {
        requireNonNull(module);
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        this.module = module;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return module;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && module.equals(((Module) other).module)); // state check
    }

    @Override
    public int hashCode() {
        return module.hashCode();
    }

}
