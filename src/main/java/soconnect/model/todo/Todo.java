package soconnect.model.todo;

import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Todo in the SoConnect.
 * Guarantees: description is not null and not an empty string.
 */
public class Todo {
    private final Description description;
    private final Priority priority;

    /**
     * Constructs a Todo.
     *
     * @param description Must not be null.
     * @param priority    Must not be null.
     */
    public Todo(Description description, Priority priority) {
        requireAllNonNull(description, priority);
        this.description = description;
        this.priority = priority;
    }

    public Description getDescription() {
        return this.description;
    }

    public String getDatetime() {
        return "Datetime";
    }

    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns true if both todos have the same description and priority.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) other;
        return otherTodo.getDescription().equals(description)
                && otherTodo.getPriority().equals(priority);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, priority);
    }

    @Override
    public String toString() {
        return description.toString() + " " + priority.toString();
    }

}
