package gim.model.exercise;

import static gim.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import gim.model.tag.Tag;

/**
 * Represents an Exercise in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exercise {

    // Identity fields
    private final Name name;
    private final Weight weight;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Exercise(Name name, Weight weight, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, weight, email, address, tags);
        this.name = name;
        this.weight = weight;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Weight getWeight() {
        return weight;
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
     * Returns true if both exercises have the same name.
     * This defines a weaker notion of equality between two exercises.
     */
    public boolean isSameExercise(Exercise otherExercise) {
        if (otherExercise == this) {
            return true;
        }

        return otherExercise != null
                && otherExercise.getName().equals(getName());
    }

    /**
     * Returns true if both exercises have the same identity and data fields.
     * This defines a stronger notion of equality between two exercises.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exercise)) {
            return false;
        }

        Exercise otherExercise = (Exercise) other;
        return otherExercise.getName().equals(getName())
                && otherExercise.getWeight().equals(getWeight())
                && otherExercise.getEmail().equals(getEmail())
                && otherExercise.getAddress().equals(getAddress())
                && otherExercise.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, weight, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Weight: ")
                .append(getWeight())
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
