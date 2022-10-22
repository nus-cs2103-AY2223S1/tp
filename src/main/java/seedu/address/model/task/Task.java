package seedu.address.model.task;

/**
 * Task is an interface for ToDo and Deadline class to implement.
 */
public interface Task {
    /**
     * Returns true if both comments have the same identity and data fields.
     * This defines a stronger notion of equality between two comments.
     */
    boolean equals(Object other);

    /**
     * Returns string representation of the Task.
     *
     */
    String toString();

    TaskTitle getTitle();

    TaskDescription getDescription();
}
