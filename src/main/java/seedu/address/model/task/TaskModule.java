package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * TaskModule class represents the module of the task.
 */
public class TaskModule {
    public static final String MESSAGE_CONSTRAINTS =
            "Module code should be at least 6 "
                    + "characters long, with first two characters "
                    + "being alphabetic characters";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z]{2}[A-Za-z0-9]*";

    public final String moduleCode;

    /**
     * Constructs a {@code Module}.
     *
     * @param module A valid module.
     */
    public TaskModule(String module) {
        requireNonNull(module);
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        moduleCode = module;
    }

    /**
     * Returns true if a given string is a valid module.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() >= 6;
    }

    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskModule // instanceof handles nulls
                && moduleCode.equals(((TaskModule) other).moduleCode)); // state check
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }
}
