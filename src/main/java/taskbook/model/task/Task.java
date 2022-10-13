package taskbook.model.task;

import java.util.Objects;

import taskbook.commons.util.CollectionUtil;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.enums.Assignment;

/**
 * Represents a Task in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task {

    private final Name name;
    private final Assignment assignment;
    private final Description description;
    private final boolean isDone;

    /**
     * Every field must be present and not null.
     */
    protected Task(Person person, Assignment assignment, Description description, boolean isDone) {
        CollectionUtil.requireAllNonNull(person, assignment, description, isDone);
        this.name = person.getName();
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Every field must be present and not null.
     */
    protected Task(Name name, Assignment assignment, Description description, boolean isDone) {
        CollectionUtil.requireAllNonNull(name, assignment, description, isDone);
        this.name = name;
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
    }

    public Name getName() {
        return name;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Description getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the completion status of the task.
     * @return [X] if the task is done. Otherwise, return [ ].
     */
    public String getStatus() {
        return isDone() ? "[X]" : "[ ]";
    }

    /**
     * Returns true if both tasks have the same description, person and assignment.
     * This defines a weaker notion of equality between two tasks.
     */
    public abstract boolean isSameTask(Task other);

    /**
     * Returns true if both tasks have the data and status fields.
     * This defines a stronger notion of equality between two tasks.
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
        return otherTask.getName().equals(getName())
                && otherTask.getAssignment().equals(getAssignment())
                && otherTask.getDescription().equals(getDescription())
                && (otherTask.isDone() == (isDone()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, assignment, description, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("[%s]", isDone()))
                .append(String.format("[%s]", getAssignment()))
                .append(String.format("[%s]", getName()))
                .append("\n")
                .append(getDescription());

        return builder.toString();
    }
}

