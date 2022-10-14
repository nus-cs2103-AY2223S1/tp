package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // identity fields
    private final Title title;
    private final boolean isCompleted; // defaults to false if not specified


    // data fields
    private final Deadline deadline; // optional
    private final Set<Contact> assignedContacts = new HashSet<>();

    /**
     * Creates a new {@code Task} with no assigned contacts and an unspecified deadline.
     *
     * @param title Title of task
     */
    public Task(Title title) {
        this(title, false, null, new HashSet<Contact>());
    }

    /**
     * Every field except the task's deadline must be present and not null.
     */
    public Task(Title title, boolean isCompleted, Deadline deadline, Set<Contact> assignedContacts) {
        requireAllNonNull(title, isCompleted, assignedContacts);

        this.title = title;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
        this.assignedContacts.addAll(assignedContacts);
    }

    public Title getTitle() {
        return title;
    }

    /**
     * Returns true if task is completed, false if not complete.
     */
    public boolean getCompleted() {
        return isCompleted;
    }


    /**
     * Returns the deadline set for the task.
     */
    public Optional<Deadline> getDeadline() {
        return Optional.ofNullable(deadline);
    }

    /**
     * Returns set of contacts assigned to task.
     */
    public Set<Contact> getAssignedContacts() {
        return Collections.unmodifiableSet(assignedContacts);
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
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getCompleted() == getCompleted()
                && otherTask.getAssignedContacts().equals(getAssignedContacts());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, isCompleted, assignedContacts);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Status: ")
                .append(getCompleted());

        Set<Contact> contacts = getAssignedContacts();
        if (!contacts.isEmpty()) {
            builder.append("; Contacts: ");
            contacts.forEach(builder::append);
        }

        return builder.toString();
    }

}
