package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task for a Patient.
 * Guarantees: immutable;
 */
public class Task {
    public static final String MESSAGE_CONSTRAINTS =
            "Task Description should only contain alphanumeric characters and spaces";

    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]*";
    public final String taskDescription;

    /**
     * Constructs a {@code Task}.
     *
     * @param description A valid task description.
     */
    public Task(String description) {
        requireNonNull(description);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        taskDescription = description;
    }

    /**
     * Checks the validity of the task description.
     *
     * @param test the string to be tested.
     * @return true if valid.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && taskDescription.equals(((Task) other).taskDescription)); // state check
    }

    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }
}
