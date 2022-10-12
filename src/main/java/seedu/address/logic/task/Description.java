package seedu.address.logic.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the description of the task in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDescription(String)}
 */
public class Description {

    private static final String MESSAGE_CONSTRAINTS =
            "Task Descriptions can take any values, and it should not be blank";

    /*
     * The first character of the taskname must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[^\\s].*";

    private final String taskDescription;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Description(String name) {
        requireNonNull(name);
        checkArgument(isValidTaskDescription(name), MESSAGE_CONSTRAINTS);
        taskDescription = name;
    }

    /**
     * Returns the description of task.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns true if task description is valid.
     *
     * @param test String to test.
     * @return Whether the string is a valid task description.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of the task description.
     *
     * @return Task description.
     */
    @Override
    public String toString() {
        return taskDescription;
    }

    /**
     * Compares another object with the Description object.
     *
     * @param other The other object to be compared to.
     * @return If the two objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && taskDescription.equals(((Description) other).taskDescription)); // state check
    }

    /**
     * Returns hashcode of the current object
     *
     * @return Hashcode of the object.
     */
    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }

}
