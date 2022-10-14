package seedu.address.model.entry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Entry in the penny wise application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Entry {
    // Identity fields
    private final Description description;
    // private final EntryType type;
    // Data fields
    private final Date date;
    private final Amount amount;
    private final Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Entry(Description description, Date date, Amount amount, Tag tag) {
        requireAllNonNull(date, amount, description, tag);
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.tag = tag;
    }

    // Getters for Entries
    public Description getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Amount getAmount() {
        return amount;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, date, amount, tag);
    }


    /**
     * Returns true if both Entries have the same description.
     * This defines a weaker notion of equality between two descriptions.
     */
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        return otherEntry != null
                && otherEntry.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both entries have the same identity and data fields.
     * This defines a stronger notion of equality between two entries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Entry)) {
            return false;
        }

        Entry otherEntry = (Entry) other;
        return otherEntry.getDescription().equals(getDescription())
                && otherEntry.getDate().equals(getDate())
                && otherEntry.getAmount().equals(getAmount())
                && otherEntry.getTag().equals(getTag());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Date: ")
                .append(getDate())
                .append("; Amount: ")
                .append(getAmount());

        Tag tag = getTag();
        if (!tag.equals(null)) {
            builder.append("; Tag: ");
            builder.append(tag);
        }
        return builder.toString();
    }
}
