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
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(String title, String deadline, Set<Tag> tags) {
        requireAllNonNull(title, deadline, tags);
        this.title = title;
        this.deadline = deadline;
        this.tags.addAll(tags);
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, deadline, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Title: ")
                .append(getDeadline())
                .append("; Deadline: ");

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
