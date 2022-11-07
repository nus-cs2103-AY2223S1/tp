package foodwhere.model.review;

import java.util.HashSet;
import java.util.Set;

import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.util.SampleDataUtil;

/**
 * A utility class to help with building Review objects.
 */
public class ReviewBuilder {

    public static final String DEFAULT_NAME = "Untitled Review";
    public static final String DEFAULT_ADDRESS = "Unknown Location";
    public static final String DEFAULT_DATE = "1/1/1970";
    public static final String DEFAULT_CONTENT = "No comment.";
    public static final String DEFAULT_RATING = "3";

    private Name name;
    private Address address;
    private Date date;
    private Content content;
    private Rating rating;
    private Set<Tag> tags;

    /**
     * Creates a {@code ReviewBuilder} with the default details.
     */
    public ReviewBuilder() {
        name = new Name(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        date = new Date(DEFAULT_DATE);
        content = new Content(DEFAULT_CONTENT);
        rating = new Rating(DEFAULT_RATING);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ReviewBuilder with the data of {@code reviewToCopy}.
     */
    public ReviewBuilder(Review reviewToCopy) {
        name = reviewToCopy.getName();
        address = reviewToCopy.getAddress();
        date = reviewToCopy.getDate();
        content = reviewToCopy.getContent();
        rating = reviewToCopy.getRating();
        tags = new HashSet<>(reviewToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Review} that we are building.
     */
    public ReviewBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Review} that we are building.
     */
    public ReviewBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Review} that we are building.
     */
    public ReviewBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Content} of the {@code Review} that we are building.
     */
    public ReviewBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Review} that we are building.
     */
    public ReviewBuilder withRating(Integer rating) {
        this.rating = new Rating(String.valueOf(rating));
        return this;
    }

    /**
     * Parses the {@code tag} into a {@code Set<Tag>} and set it to the {@code Review} that we are building.
     */
    public ReviewBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Review build() {
        return new Review(name, address, date, content, rating, tags);
    }

}
