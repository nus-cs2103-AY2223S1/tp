package foodwhere.testutil;

import java.util.HashSet;
import java.util.Set;

import foodwhere.model.commons.Detail;
import foodwhere.model.commons.Name;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Stall;
import foodwhere.model.util.SampleDataUtil;

/**
 * A utility class to help with building Stall objects.
 */
public class StallBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Address address;
    private Set<Detail> details;
    private Set<Review> reviews;

    /**
     * Creates a {@code StallBuilder} with the default details.
     */
    public StallBuilder() {
        name = new Name(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        details = new HashSet<>();
        reviews = new HashSet<>();
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public StallBuilder(Stall stallToCopy) {
        name = stallToCopy.getName();
        address = stallToCopy.getAddress();
        details = new HashSet<>(stallToCopy.getDetails());
        reviews = new HashSet<>(stallToCopy.getReviews());
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public StallBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code details} into a {@code Set<Detail>} and set it to the {@code Stall} that we are building.
     */
    public StallBuilder withDetails(String ... details) {
        this.details = SampleDataUtil.getDetailSet(details);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Stall} that we are building.
     */
    public StallBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Review} of the {@code Stall} that we are building.
     */
    public StallBuilder withReview(Review ... reviews) {
        this.reviews = SampleDataUtil.getReviewSet(reviews);
        return this;
    }

    public Stall build() {
        return new Stall(name, address, details, reviews);
    }

}
