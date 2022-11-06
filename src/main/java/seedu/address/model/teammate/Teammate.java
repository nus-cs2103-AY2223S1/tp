package seedu.address.model.teammate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Teammate in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Teammate {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Teammate(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
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
     * Returns true if both teammates have the same name.
     * This defines a weaker notion of equality between two teammates.
     */
    public boolean isSameTeammate(Teammate otherTeammate) {
        if (otherTeammate == this) {
            return true;
        }

        return otherTeammate != null
                && otherTeammate.getName().equals(getName());
    }

    /**
     * Returns true if both teammates have the same identity and data fields.
     * This defines a stronger notion of equality between two teammates.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Teammate)) {
            return false;
        }

        Teammate otherTeammate = (Teammate) other;
        return otherTeammate.getName().equals(getName())
                && otherTeammate.getPhone().equals(getPhone())
                && otherTeammate.getEmail().equals(getEmail())
                && otherTeammate.getAddress().equals(getAddress())
                && otherTeammate.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
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
