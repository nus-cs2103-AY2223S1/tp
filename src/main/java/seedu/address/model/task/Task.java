package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

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
    private final Project project; // optional
    private final Set<Contact> assignedContacts;

    /**
     * Creates a new {@code Task} given the title.
     * @param title title of task
     */
    public Task(Title title) {
        this(title, Deadline.UNSPECIFIED, Project.UNSPECIFIED, new HashSet<>());
    }

    /**
     * Creates a new {@code Task}.
     *
     * @param title Title of task
     */
    public Task(Title title, Deadline deadline, Project project, Set<Contact> assignedContacts) {
        this(title, false, deadline, project, assignedContacts);
    }

    /**
     * Every field except the task's deadline must be present and not null.
     */
    public Task(Title title, boolean isCompleted, Deadline deadline, Project project, Set<Contact> assignedContacts) {
        requireAllNonNull(title, isCompleted, project, assignedContacts);

        this.title = title;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
        this.project = project;
        this.assignedContacts = assignedContacts;
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
    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns the project the task belongs in.
     */
    public Project getProject() {
        return project;
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
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getProject().equals(getProject())
                && otherTask.getAssignedContacts().equals(getAssignedContacts());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, isCompleted, deadline, project, assignedContacts);
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
            List<Contact> contactList = new ArrayList<>(contacts);
            builder.append(contactList.get(0));
            for (int i = 1; i < contactList.size(); i++) {
                builder.append(", " + contactList.get(i).toString());
            }
        }

        if (!deadline.isUnspecified()) {
            builder.append("; Deadline: ");
            builder.append(deadline);
        }

        if (!project.isUnspecified()) {
            builder.append("; Project: ");
            builder.append(project);
        }

        return builder.toString();
    }

}
