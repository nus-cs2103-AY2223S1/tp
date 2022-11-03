package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Deadline in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deadline extends Task {
    private FormatDate date;

    /**
     * Every field must be present and not null.
     */
    public Deadline(TaskTitle title, TaskDescription description, FormatDate date) {
        super(title, description);
        requireNonNull(date);
        this.date = date;
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
        return super.equals(otherStudent)
                && otherStudent.getDate().equals(getDate());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append("; Date: ")
                .append(getDate().toString());

        return builder.toString();
    }
}
