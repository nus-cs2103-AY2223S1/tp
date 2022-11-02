package foodwhere.model.stall;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.model.review.exceptions.DuplicateReviewException;
import foodwhere.model.review.exceptions.ReviewNotFoundException;
import foodwhere.model.util.SampleDataUtil;

/**
 * A utility class to help with building Stall objects.
 */
public class StallBuilder {

    public static final String DEFAULT_NAME = "Unnamed Stall";
    public static final String DEFAULT_ADDRESS = "Unknown Location";

    private Name name;
    private Address address;
    private Set<Tag> tags;
    private HashSet<Review> reviews;

    /**
     * Creates a {@code StallBuilder} with the default details.
     */
    public StallBuilder() {
        name = new Name(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        reviews = new HashSet<>();
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public StallBuilder(Stall stallToCopy) {
        name = stallToCopy.getName();
        address = stallToCopy.getAddress();
        tags = new HashSet<>(stallToCopy.getTags());
        reviews = new HashSet<>(stallToCopy.getReviews());
    }

    /**
     * Sets the {@code name} of the {@code Stall} that we are building.
     */
    public StallBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Stall} that we are building.
     */
    public StallBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code address} of the {@code Stall} that we are building.
     */
    public StallBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code reviews} of the {@code Stall} that we are building.
     */
    public StallBuilder withReviews(Review ... reviews) {
        requireNonNull(reviews);
        for (Review review : reviews) {
            requireNonNull(review);
        }
        this.reviews = new HashSet<>(List.of(reviews));
        return this;
    }

    /**
     * Adds a review to the {@code Stall} that we are building.
     */
    public StallBuilder addReview(Review review) {
        requireNonNull(review);
        if (this.reviews.contains(review)) {
            throw new DuplicateReviewException();
        }
        this.reviews.add(review);
        return this;
    }

    /**
     * Removes a review from the {@code Stall} that we are building.
     */
    public StallBuilder removeReview(Review review) {
        requireNonNull(review);
        if (!this.reviews.contains(review)) {
            throw new ReviewNotFoundException();
        }
        this.reviews.remove(review);
        return this;
    }

    /**
     * Builds a stall.
     * @return Stall with the stored data.
     */
    public Stall build() {
        HashSet<Review> namedReviews = new HashSet<>();
        for (Review review : reviews) {
            Review namedReview = new ReviewBuilder(review)
                    .withName(name.fullName).build();
            namedReviews.add(namedReview);
        }
        return new Stall(name, address, tags, namedReviews);
    }
}
