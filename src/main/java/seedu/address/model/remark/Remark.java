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
    private final Text text;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Remark(Text text, Set<Tag> tags) {
        requireAllNonNull(text, tags);
        this.text = text;

        this.tags.addAll(tags);
    }

    public Text getText() {
        return text;
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
                && otherRemark.getText().value.equals(getText().value);
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
        return otherRemark.getText().value.equals(getText().value)
                && otherRemark.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(text, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getText());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
