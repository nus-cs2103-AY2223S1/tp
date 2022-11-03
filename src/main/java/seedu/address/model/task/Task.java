package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Task is an abstract class for ToDo, Deadline and Assignment class to extend.
 */
public abstract class Task {
    private TaskTitle title;
    private TaskDescription description;

    public Task(TaskTitle title, TaskDescription description) {
        requireAllNonNull(title, description);
        this.title = title;
        this.description = description;
    }

    public TaskTitle getTitle() {
        return title;
    };

    public TaskDescription getDescription() {
        return description;
    };

    /**
     * Returns true if both comments have the same identity and data fields.
     * This defines a stronger notion of equality between two comments.
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
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription());
    };

    /**
     * Returns string representation of the Task.
     *
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        return builder.toString();
    };

}
