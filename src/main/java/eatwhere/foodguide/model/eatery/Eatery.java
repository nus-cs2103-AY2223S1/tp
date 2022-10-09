package eatwhere.foodguide.model.eatery;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import eatwhere.foodguide.commons.util.CollectionUtil;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Represents an Eatery in the food guide.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Eatery {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Eatery(Name name, Phone phone, Email email, Location location, Set<Tag> tags) {
        CollectionUtil.requireAllNonNull(name, phone, email, location, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * TODO: change this to better reflect a weaker notation of equality between eateries.
     * Returns true if both eateries have the same name.
     * This defines a weaker notion of equality between two eateries.
     */
    public boolean isSameEatery(Eatery otherEatery) {
        if (otherEatery == this) {
            return true;
        }

        return otherEatery != null
                && otherEatery.getName().equals(getName());
    }

    /**
     * Returns true if both eateries have the same identity and data fields.
     * This defines a stronger notion of equality between two eateries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Eatery)) {
            return false;
        }

        Eatery otherEatery = (Eatery) other;
        return otherEatery.getName().equals(getName())
                && otherEatery.getPhone().equals(getPhone())
                && otherEatery.getEmail().equals(getEmail())
                && otherEatery.getLocation().equals(getLocation())
                && otherEatery.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, location, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Location: ")
                .append(getLocation());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
