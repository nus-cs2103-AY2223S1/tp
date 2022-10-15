package foodwhere.model.review;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;

/**
 * Represents a Review in the address book.
 * Guarantees: tags are present and not null, field values are validated, immutable.
 */
public class Review {

    // Identity fields
    private final Name name;

    // Data fields
    private final Date date;
    private final Content content;
    private final Rating rating;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Review(Name name, Date date, Content content, Rating rating, Set<Tag> tags) {
        requireAllNonNull(name, date, content, tags);
        this.name = name;
        this.date = date;
        this.content = content;
        this.rating = rating;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Content getContent() {
        return content;
    }

    public Rating getRating() {
        return rating;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both review have the same stall name.
     * This defines a weaker notion of equality between two reviews.
     */
    public boolean isSameReview(Review otherReview) {
        if (otherReview == this) {
            return true;
        }

        return otherReview != null
                && otherReview.getName().equals(getName())
                && otherReview.getDate().equals(getDate())
                && otherReview.getContent().equals(getContent())
                && otherReview.getRating().equals(getRating())
                && otherReview.getTags().equals(getTags());
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

        if (!(other instanceof Review)) {
            return false;
        }

        Review otherStall = (Review) other;
        return otherStall.getName().equals(getName())
                && otherStall.getDate().equals(getDate())
                && otherStall.getContent().equals(getContent())
                && otherStall.getRating().equals(getRating())
                && otherStall.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, content, rating, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Date: ")
                .append(getDate())
                .append("; Content: ")
                .append(getContent())
                .append("; Rating: ")
                .append(getRating());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
