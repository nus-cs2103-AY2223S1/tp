package seedu.address.model.module.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTaskDescription(String)}.
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS = "Task descriptions "
            + "can take any values but cannot be blank.";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String taskDescription;

    /**
     * Constructs a {@code Task}.
     * @param taskDescription A valid task description.
     */
    public Task(String taskDescription) {
        requireNonNull(taskDescription);
        checkArgument(isValidTaskDescription(taskDescription), MESSAGE_CONSTRAINTS);
        this.taskDescription = taskDescription;
    }

    /**
     * Returns true if a given {@code String} is a valid task description.
     */
    public static boolean isValidTaskDescription(String test) {
        if (test == null) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the task's description.
     * @return Description of the task.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskDescription().equals(getTaskDescription());
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Task && taskDescription.equals(((Task) other).taskDescription));
    }

    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + taskDescription + ']';
    }

}
