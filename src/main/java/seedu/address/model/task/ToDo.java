package seedu.address.model.task;

/**
 * Represents a ToDo in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ToDo extends Task {

    /**
     * Every field must be present and not null.
     */
    public ToDo(TaskTitle title, TaskDescription description) {
        super(title, description);
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
        return super.equals(otherStudent);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
