package swift.model.task;

import static swift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a Task in the address book.
 */
public abstract class Task {
    private final UUID id;

    private final TaskName name;

    private final Description description;

    /**
     * Constructs a {@code Task}.
     *
     * @param id   A unique identifier for the task.
     * @param name A valid task name.
     */
    public Task(UUID id, TaskName name, Description description) {
        requireAllNonNull(id, name);
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public TaskName getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Task
                && name.equals(((Task) other).name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + name.toString() + ']';
    }
}
