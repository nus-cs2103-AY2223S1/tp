package foodwhere.testutil;

import java.util.HashSet;
import java.util.Set;

import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Review;
import foodwhere.model.util.SampleDataUtil;

/**
 * A utility class to help with building Review objects.
 */
public class ReviewBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DATE = "1/1/2020";
    public static final String DEFAULT_CONTENT = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Date date;
    private Content content;
    private Set<Tag> tags;

    /**
     * Creates a {@code ReviewBuilder} with the default details.
     */
    public ReviewBuilder() {
        name = new Name(DEFAULT_NAME);
        date = new Date(DEFAULT_DATE);
        content = new Content(DEFAULT_CONTENT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ReviewBuilder with the data of {@code reviewToCopy}.
     */
    public ReviewBuilder(Review reviewToCopy) {
        name = new Name(reviewToCopy.getName().fullName);
        date = reviewToCopy.getDate();
        content = reviewToCopy.getContent();
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
     * Parses the {@code tag} into a {@code Set<Tag>} and set it to the {@code Review} that we are building.
     */
    public ReviewBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Review build() {
        return new Review(name, date, content, tags);
    }

}
