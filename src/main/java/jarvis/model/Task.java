package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in JARVIS.
 * Guarantees: details are present and not null.
 */
public class Task {

    // Identity fields
    private final TaskDesc taskDesc;

    // Data fields
    private final TaskDeadline taskDeadline;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskDesc taskDesc, TaskDeadline taskDeadline) {
        requireAllNonNull(taskDesc, taskDeadline);
        this.taskDesc = taskDesc;
        this.taskDeadline = taskDeadline;
    }

    public TaskDesc getDesc() {
        return taskDesc;
    }

    public TaskDeadline getDeadline() {
        return taskDeadline;
    }

    /**
     * Returns true if both task have the same description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDesc().equals(getDesc());
    }

    /**
     * Returns true if both tasks have the same description and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDesc().equals(getDesc()) && otherTask.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskDesc);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDesc());
        if (!getDeadline().deadline.isEmpty()) {
            builder.append("\nDeadline: ").append(getDeadline());
        }
        return builder.toString();
    }

}
