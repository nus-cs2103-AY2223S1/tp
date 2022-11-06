package swift.model.task;

import static swift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a Task in the address book.
 */
public class Task implements Comparable<Task> {
    private final UUID id;

    private final TaskName name;

    private final Optional<Description> description;

    private final Optional<Deadline> deadline;

    private final boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param id          A unique identifier for the task.
     * @param name        A valid task name.
     * @param description A valid and optional description.
     * @param deadline    A valid and optional deadline.
     */
    public Task(UUID id, TaskName name, Optional<Description> description, Optional<Deadline> deadline) {
        requireAllNonNull(id, name, description, deadline);
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task}.
     *
     * @param id A unique identifier for the task.
     * @param name A valid task name.
     * @param description A valid and optional description.
     * @param deadline A valid and optional deadline.
     * @param isDone A boolean value indicating whether the task is done.
     */
    public Task(UUID id, TaskName name, Optional<Description> description,
            Optional<Deadline> deadline, boolean isDone) {
        requireAllNonNull(id, name, description, deadline);
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public UUID getId() {
        return id;
    }

    public TaskName getName() {
        return name;
    }

    public Optional<Description> getDescription() {
        return description;
    }

    public Optional<Deadline> getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public int compareTo(Task other) {
        // Compare by name lexicographically if both tasks do not have deadlines
        if (deadline.isEmpty() && other.deadline.isEmpty()) {
            return name.compareTo(other.name);
        }

        if (deadline.isEmpty()) {
            return 1;
        }
        if (other.deadline.isEmpty()) {
            return -1;
        }

        // Compare by deadline if both tasks have deadlines
        return deadline.get().compareTo(other.deadline.get());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Task) {
            Task otherTask = (Task) other;
            return name.equals(otherTask.name)
                    && description.equals(otherTask.description)
                    && deadline.equals(otherTask.deadline);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isDone);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + name.toString() + ']';
    }
}
