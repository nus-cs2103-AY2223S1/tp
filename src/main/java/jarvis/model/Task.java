package jarvis.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Task in JARVIS.
 * Guarantees: task description is present and not null.
 */
public class Task implements Comparable<Task> {

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

    public boolean hasDeadline() {
        return taskDeadline != null;
    }

    public LocalDate getDeadline() {
        return hasDeadline() ? taskDeadline.deadline : null;
    }

    public String getDeadlineString() {
        return hasDeadline() ? taskDeadline.toString() : "";
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

    @Override
    public int compareTo(Task t) {
        if (isDone != t.isDone()) {
            return isDone ? 1 : -1;
        }
        if (this.hasDeadline() != t.hasDeadline()) {
            return this.hasDeadline() ? -1 : 1;
        }
        if (!(this.hasDeadline() || t.hasDeadline())) {
            return 0;
        }
        if (isDone) {
            return t.getDeadline().compareTo(this.getDeadline());
        } else {
            return this.getDeadline().compareTo(t.getDeadline());
        }
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

        boolean taskDeadlineEquality;
        if (!hasDeadline()) {
            taskDeadlineEquality = otherTask.taskDeadline == null;
        } else {
            taskDeadlineEquality = taskDeadline.equals(otherTask.taskDeadline);
        }
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
        builder.append(getDesc());
        if (hasDeadline()) {
            builder.append("\nDeadline: ").append(taskDeadline);
        }
        return builder.toString();
    }

}
