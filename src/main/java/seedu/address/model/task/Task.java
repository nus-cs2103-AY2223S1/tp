package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTaskName(String)}
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS = "Task names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String taskName;

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName A valid task name.
     */
    public Task(String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = taskName;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && taskName.equals(((Task) other).taskName)); // state check
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + taskName + ']';
    }

}

