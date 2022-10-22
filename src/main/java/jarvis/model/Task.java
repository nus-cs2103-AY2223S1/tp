package jarvis.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Task in JARVIS.
 * Guarantees: task description is present and not null.
 */
public class Task {

    // Identity fields
    private final TaskDesc taskDesc;

    // Data fields
    private final TaskDeadline taskDeadline;
    private boolean isDone;

    /**
     * Task description must be present and not null.
     */
    public Task(TaskDesc taskDesc, TaskDeadline taskDeadline) {
        requireNonNull(taskDesc);
        this.taskDesc = taskDesc;
        this.taskDeadline = taskDeadline;
        this.isDone = false;
    }

    public TaskDesc getDesc() {
        return taskDesc;
    }

    public LocalDate getDeadline() {
        return taskDeadline == null ? null : taskDeadline.deadline;
    }

    public String getDeadlineString() {
        return taskDeadline == null ? "" : taskDeadline.toString();
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
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

        boolean taskDeadlineEquality = taskDeadline == null || otherTask.taskDeadline == null
                ? taskDeadline == null && otherTask.taskDeadline == null
                : otherTask.taskDeadline.equals(taskDeadline);
        return otherTask.taskDesc.equals(taskDesc)
                && taskDeadlineEquality
                && otherTask.isDone == isDone;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskDesc);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (isDone) {
            builder.append("[X] ");
        } else {
            builder.append("[ ] ");
        }
        builder.append(getDesc());
        if (taskDeadline != null) {
            builder.append("\nDeadline: ").append(taskDeadline);
        }
        return builder.toString();
    }

}
