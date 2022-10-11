package seedu.address.model.task;

import seedu.address.model.person.Person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // identity fields
    private final Title title;
    private final boolean marked;

    private final Set<Person> assignedContacts = new HashSet<>();;

    /**
     * Creates a new Task with no assigned contacts. (Used when a new task is added)
     * @param title Title of task.
     * @param marked Whether task is completed.
     */
    public Task(Title title, boolean marked) {
        requireAllNonNull(title, marked);

        this.title = title;
        this.marked = marked; // task is default as unmarked
    }

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, boolean marked, Set<Person> assignedContacts) {
        requireAllNonNull(title, marked, assignedContacts);

        this.title = title;
        this.marked = marked; // task is default as unmarked
        this.assignedContacts.addAll(assignedContacts);
    }

    public Title getTitle() {
        return title;
    }

    /**
     * Returns true if task is marked, false if not marked.
     */
    public boolean getMarkStatus() {
        return this.marked;
    }

    /**
     * Returns set of contacts assigned to task.
     */
    public Set<Person> getAssignedContacts() {
        return this.assignedContacts;
    }

    /**
     * Returns true if both task have the same title and size.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both tasks have the same data fields.
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
        return otherTask.getTitle().equals(getTitle());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle()).append(";");

        return builder.toString();
    }

}
