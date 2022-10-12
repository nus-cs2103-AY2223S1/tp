package seedu.address.logic.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of the task in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskName(String)}
 */
public class TaskName {

    private static final String MESSAGE_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the taskname must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String taskName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TaskName(String name) {
        requireNonNull(name);
        checkArgument(isValidTaskName(name), MESSAGE_CONSTRAINTS);
        taskName = name;
    }

    /**
     * Returns true if task name is valid.
     *
     * @param test String to test.
     * @return Whether the string is a valid task name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of the task description.
     *
     * @return Task name.
     */
    @Override
    public String toString() {
        return taskName;
    }

    /**
     * Compares another object with the Name object.
     *
     * @param other The other object to be compared to.
     * @return If the two objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskName // instanceof handles nulls
                && taskName.equals(((TaskName) other).taskName)); // state check
    }

    /**
     * Returns the name of a task.
     *
     * @return The task description.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns hashcode of the current object
     *
     * @return Hashcode of the object.
     */
    @Override
    public int hashCode() {
        return taskName.hashCode();
    }

}
