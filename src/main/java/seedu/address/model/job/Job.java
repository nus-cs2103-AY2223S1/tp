package seedu.address.model.job;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Job in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    // Identity fields
    public final String value;
    private final Id id;
    private final Title title;

    /**
     * Every field must be present and not null.
     */
    public Job(Id id, Title title) {
        requireAllNonNull(id, title);
        this.id = id;
        this.title = title;
        value = String.format("%s - %s", id.value, title.value);
    }

    public Id getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    /**
     * Returns true if both jobs have the same id.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && otherJob.getId().equals(getId());
    }

    /**
     * Returns true if both jobs have the same id and title.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return otherJob.getId().equals(getId())
                && otherJob.getTitle().equals(getTitle());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getId())
                .append("; Job Title: ")
                .append(getTitle());

        return builder.toString();
    }

}
