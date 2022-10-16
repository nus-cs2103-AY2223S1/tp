package soconnect.model.todo;

import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Todo in the SoConnect.
 * Guarantees: description is not null and not an empty string.
 */
public class Todo {
    private final String description;

    /**
     * Constructs a Todo.
     *
     * @param description Must not be null.
     */
    public Todo(String description) {
        requireAllNonNull(description);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDatetime() {
        return "Datetime";
    }

    public String getPriority() {
        return "Priority";
    }

    /**
     * Returns true if both todos have the same description.
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
        return otherTodo.getDescription().equals(description);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description;
    }

}
