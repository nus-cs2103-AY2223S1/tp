package modtrekt.model.module;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's number of tasks.
 * Guarantees: immutable; is valid as declared in {@link #isValidCount(String)}
 */
public class ModTaskCount {

    public static final String MESSAGE_CONSTRAINTS =
            "Task count should only contain numeric characters and it should not be blank";

    /*
     * The first character of the task count must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    private final String taskCount;

    /**
     * Constructs a {@code ModTaskCount}.
     *
     * @param count A valid number of tasks.
     */
    public ModTaskCount(String count) {
        requireNonNull(count);
        checkArgument(isValidCount(count), MESSAGE_CONSTRAINTS);
        taskCount = count;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskCount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModTaskCount // instanceof handles nulls
                && taskCount.equals(((ModTaskCount) other).taskCount)); // state check
    }

    @Override
    public int hashCode() {
        return taskCount.hashCode();
    }

    public String getTaskCount() {
        return taskCount;
    }
}
