package seedu.hrpro.model.task;

import static seedu.hrpro.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.hrpro.model.deadline.Deadline;

/**
 * Represents a Task in the TaskList.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task implements Comparable<Task> {

    // Identity fields
    private final Deadline deadline;
    private final TaskDescription description;
    private final TaskMark mark;

    /**
     * Every field must be present and not null.
     */
    public Task(Deadline deadline, TaskDescription description, TaskMark mark) {
        requireAllNonNull(deadline, description);
        this.deadline = deadline;
        this.description = description;
        this.mark = mark;
    }

    public Deadline getTaskDeadline() {
        return deadline;
    }

    public TaskDescription getTaskDescription() {
        return description;
    }

    public TaskMark getTaskMark() {
        return mark;
    }

    /**
     * Returns true if both tasks have the same description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskDescription().equals(getTaskDescription());
    }

    /**
     * Returns true if both tasks have the same deadline and description.
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
        return otherTask.getTaskDescription().equals(getTaskDescription())
                && otherTask.getTaskDeadline().equals(getTaskDeadline())
                && otherTask.getTaskMark().equals(getTaskMark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(deadline, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskDescription())
                .append("; Task Deadline: ")
                .append(getTaskDeadline())
                .append("; Task Completed: ")
                .append(getTaskMark());

        return builder.toString();
    }

    /**
     * Compares tasks by deadline.
     * @param other
     */
    @Override
    public int compareTo(Task other) {
        return this.getTaskDeadline().compareTo(other.getTaskDeadline());
    }

}
