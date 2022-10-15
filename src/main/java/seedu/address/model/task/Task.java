package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in Salesy
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final String title;

    // Data fields
    private final String deadline;
    private final boolean status;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a class with the default of status being false.
     */
    public Task(String title, String deadline, Set<Tag>tags) {
        this(title, deadline, false, tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Task(String title, String deadline, boolean status, Set<Tag> tags) {
        requireAllNonNull(title, deadline, status, tags);
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.tags.addAll(tags);
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean getStatus() {
        return status;
    }

    public String getParsedStatus() {
        if (status) {
            return "Completed";
        } else {
            return "Not Done";
        }
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, deadline, status, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append("; Deadline: ")
                .append(getDeadline())
                .append(", Status: ")
                .append(getParsedStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getTags().equals(getTags());
    }
}
