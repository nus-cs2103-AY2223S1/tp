package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a deadline in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deadline implements Task {
    private TaskTitle title;
    private TaskDescription description;
    private FormatDate date;

    /**
     * Every field must be present and not null.
     */
    public Deadline(TaskTitle title, TaskDescription description, FormatDate date) {
        requireAllNonNull(title, description, date);
        this.title = title;
        this.description = description;
        this.date = date;
    }

    @Override
    public TaskTitle getTitle() {
        return title;
    }

    @Override
    public TaskDescription getDescription() {
        return description;
    }

    public FormatDate getDate() {
        return date;
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

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherStudent = (Deadline) other;
        return otherStudent.getTitle().equals(getTitle())
                && otherStudent.getDescription().equals(getDescription())
                && otherStudent.getDate().equals(getDate());
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
                .append(getDescription())
                .append("; Date: ")
                .append(getDate().toString());

        return builder.toString();
    }
}
