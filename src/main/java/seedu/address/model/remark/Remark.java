package seedu.address.model.remark;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Point of contact in the client.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Remark {

    // Identity fields
    private final RemarkName name;
    private final RemarkAddress address;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Remark(RemarkName name, RemarkAddress address, Set<Tag> tags) {
        requireAllNonNull(name, address, tags);
        this.name = name;
        this.address = address;

        this.tags.addAll(tags);
    }

    public RemarkName getName() {
        return name;
    }

    public RemarkAddress getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both remarks have the same name.
     * This defines a weaker notion of equality between two remarks.
     */
    public boolean isSameRemark(Remark otherRemark) {
        if (otherRemark == this) {
            return true;
        }

        return otherRemark != null
                && otherRemark.getName().equals(getName());
    }

    /**
     * Returns true if both remarks have the same identity and data fields.
     * This defines a stronger notion of equality between two remarks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return otherRemark.getName().equals(getName())
                && otherRemark.getAddress().equals(getAddress())
                && otherRemark.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
