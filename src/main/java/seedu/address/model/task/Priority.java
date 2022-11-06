package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents the priority of the task in the task list.
 * Guarantees: immutable;
 */
public class Priority {
    public static final String MESSAGE_CONSTRAINTS = "\"%s\" is not a valid priority! Priority must be one of the "
            + "following: " + PriorityEnum.getValidPriorities();

    private final PriorityEnum priority;

    /**
     * Constructor for priority class
     */
    public Priority(PriorityEnum priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    /**
     * Returns the string representation of the task priority.
     *
     * @return Task priority.
     */
    @Override
    public String toString() {
        return priority.name();
    }

    /**
     * Compares another object with the Priority object.
     *
     * @param other The other object to be compared to.
     * @return If the two objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && priority.equals(((Priority) other).priority)); // state check
    }

    /**
     * Returns hashcode of the current object
     *
     * @return Hashcode of the object.
     */
    @Override
    public int hashCode() {
        return priority.hashCode();
    }

    /**
     * Returns the priority of a task.
     *
     * @return The task priority.
     */
    public PriorityEnum getPriority() {
        return priority;
    }

    /**
     * Returns true if priority name is valid.
     *
     * @param test String to test.
     * @return Whether the String is a valid priority name.
     */
    public static boolean isValidTaskPriority(String test) {
        return PriorityEnum.getFromString(test).isPresent();
    }

}
