package swift.model.task;

import static swift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a Task in the address book.
 */
public class Task {
    private final UUID id;
    public final TaskName taskName;

    /**
     * Constructs a {@code Task}.
     *
     * @param id       A unique identifier for the task.
     * @param taskName A valid task name.
     */
    public Task(UUID id, TaskName taskName) {
        requireAllNonNull(id, taskName);
        this.id = id;
        this.taskName = taskName;
    }

    public UUID getId() {
        return id;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Task
                && taskName.equals(((Task) other).taskName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + taskName.toString() + ']';
    }
}
