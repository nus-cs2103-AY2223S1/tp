package seedu.address.model.commission;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Commission in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Commission {

    // Identity fields
    private final Title title;
    private final Description description;
    private final Fee fee;
    private final Deadline deadline;
    private final CompletionStatus completionStatus;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Commission(Title title, Description description, Fee fee, Deadline deadline, Set<Tag> tags,
                      CompletionStatus completionStatus) {
        requireAllNonNull(title, description, fee, deadline, tags, completionStatus);
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.deadline = deadline;
        this.tags.addAll(tags);
        this.completionStatus = completionStatus;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Fee getFee() {
        return fee;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both commissions have the same title.
     * This defines a weaker notion of equality between two commissions.
     */
    public boolean isSameCommission(Commission otherCommission) {
        if (otherCommission == this) {
            return true;
        }

        return otherCommission != null
                && otherCommission.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both commissions have the same fields.
     * This defines a stronger notion of equality between two commissions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Commission)) {
            return false;
        }

        Commission otherCommission = (Commission) other;
        return otherCommission.getTitle().equals(getTitle())
                && otherCommission.getFee().equals(getFee())
                && otherCommission.getDeadline().equals(getDeadline())
                && otherCommission.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fee, deadline, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription())
                .append("; Fee: ")
                .append(getFee())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Completed:")
                .append(getCompletionStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
