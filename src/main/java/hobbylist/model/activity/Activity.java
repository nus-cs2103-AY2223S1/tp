package hobbylist.model.activity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import hobbylist.commons.util.CollectionUtil;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;

/**
 * Represents an Activity in HobbyList.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Activity {

    // Identity fields
    private final Name name;

    // Data fields
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();
    private final Optional<Date> optionalDate;
    private int rating = 0;
    private final Status status;
    private final Optional<Review> review;

    /**
     * Constructor without rating. Every field except rating must be present and not null.
     */
    public Activity(Name name, Description description, Set<Tag> tags, Optional<Date> optionalDate, Status status,
                    Optional<Review> review) {
        CollectionUtil.requireAllNonNull(name, description, optionalDate, tags);
        this.name = name;
        this.description = description;
        this.tags.addAll(tags);
        this.optionalDate = optionalDate;
        this.status = status;
        this.review = review;
    }


    /**
     * Constructor with rating. Every field must be present and not null.
     */
    public Activity(Name name, Description description, Set<Tag> tags,
                    Optional<Date> optionalDate, int rating, Status status,
                    Optional<Review> review) {
        CollectionUtil.requireAllNonNull(name,
                description, tags, optionalDate, rating);
        this.name = name;
        this.description = description;
        this.tags.addAll(tags);
        this.optionalDate = optionalDate;
        this.rating = rating;
        this.status = status;
        this.review = review;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    public Optional<Date> getDate() {
        return this.optionalDate;
    }

    public int getRating() {
        return this.rating;
    }

    public Status getStatus() {
        return status;
    }

    public Optional<Review> getReview() {
        return review;
    }

    public boolean hasStatus() {
        return status.hasStatus();
    }

    /**
     * Returns true if both activities have the same name.
     * This defines a weaker notion of equality between two activities.
     */
    public boolean isSameActivity(Activity otherActivity) {
        if (otherActivity == this) {
            return true;
        }

        return otherActivity != null
                && otherActivity.getName().equals(getName())
                && otherActivity.getDescription().equals(getDescription())
                && otherActivity.getTags().equals(getTags())
                && otherActivity.getDate().equals(getDate());
    }

    /**
     * Returns true if both activities have the same identity and data fields.
     * This defines a stronger notion of equality between two activities.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Activity)) {
            return false;
        }

        Activity otherActivity = (Activity) other;
        return otherActivity.getName().equals(getName())
                && otherActivity.getDescription().equals(getDescription())
                && otherActivity.getTags().equals(getTags())
                && otherActivity.getDate().equals(getDate())
                && otherActivity.getRating() == getRating()
                && otherActivity.getStatus().equals(getStatus())
                && otherActivity.getReview().equals(getReview());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, tags, optionalDate, status, rating, review);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        if (optionalDate.isPresent()) {
            builder.append("; Date: ")
                    .append(this.optionalDate.get().toString());
        }
        if (rating != 0) {
            builder.append("; Rating: ")
                    .append(rating);
        }
        builder.append("; Status: ")
                .append(getStatus());
        review.ifPresent(value -> builder.append("; Review: ")
                .append(value));
        return builder.toString();
    }

}
