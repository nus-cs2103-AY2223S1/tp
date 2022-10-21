package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a ToDo in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ToDo implements Task {
    private TaskTitle title;
    private TaskDescription description;

    /**
     * Every field must be present and not null.
     */
    public ToDo(TaskTitle title, TaskDescription description) {
        requireAllNonNull(title, description);
        this.title = title;
        this.description = description;
    }

    public TaskTitle getTitle() {
        return title;
    }

    public TaskDescription getDescription() {
        return description;
    }

    /**
     * Returns true if both comments have the same identity and data fields.
     * This defines a stronger notion of equality between two comments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ToDo)) {
            return false;
        }

        ToDo otherStudent = (ToDo) other;
        return otherStudent.getTitle().equals(getTitle())
                && otherStudent.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        return builder.toString();
    }
}
