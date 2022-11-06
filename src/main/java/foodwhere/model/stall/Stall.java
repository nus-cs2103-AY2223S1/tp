package foodwhere.model.stall;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Review;

/**
 * Represents a Stall in the address book.
 * Guarantees: tags are present and not null, field values are validated, immutable.
 */
public class Stall {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Review> reviews = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Stall(Name name, Address address, Set<Tag> tags) {
        requireAllNonNull(name, address, tags);
        this.name = name;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Constructor with reviews, reviews can be empty as it is optional
     */
    public Stall(Name name, Address address, Set<Tag> tags, Set<Review> reviews) {
        requireAllNonNull(name, address, tags);
        this.name = name;
        this.address = address;
        this.tags.addAll(tags);
        if (!reviews.isEmpty()) {
            this.reviews.addAll(reviews);
        }
    }

    public Name getName() {
        return name;
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
     * Returns an immutable review set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Review> getReviews() {
        return Collections.unmodifiableSet(reviews);
    }

    /**
     * Returns true if both stalls have the same name.
     * This defines a weaker notion of equality between two stalls.
     */
    public boolean isSameStall(Stall otherStall) {
        if (otherStall == this) {
            return true;
        }

        return otherStall != null
                && otherStall.getName().equals(getName())
                && otherStall.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both stalls have the same identity and data fields.
     * This defines a stronger notion of equality between two stalls.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Stall)) {
            return false;
        }

        Stall otherStall = (Stall) other;
        return otherStall.getName().equals(getName())
                && otherStall.getAddress().equals(getAddress())
                && otherStall.getTags().equals(getTags())
                && otherStall.getReviews().equals(getReviews());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, tags, reviews);
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

        Set<Review> reviews = getReviews();
        if (!reviews.isEmpty()) {
            builder.append("; Reviews: ");
            reviews.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Creates a string that contains the list of tags stored within the Stall.
     * Example: If the sets of tags are 'test1' and 'test2', the method will
     * return "test1, test2"
     *
     * @return List of tags in a string format.
     */
    public String getTagString() {
        if (!tags.isEmpty()) {
            return this.tags
                    .stream()
                    .flatMap(rev -> Stream.of(rev.tag))
                    .collect(Collectors.joining(", "));
        } else {
            return "";
        }
    }
}
