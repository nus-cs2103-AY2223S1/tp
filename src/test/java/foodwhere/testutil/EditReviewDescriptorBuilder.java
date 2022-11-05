package foodwhere.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import foodwhere.logic.commands.REditCommand;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;
import foodwhere.model.review.Review;

/**
 * A utility class to help with building EditStallDescriptor objects.
 */
public class EditReviewDescriptorBuilder {

    private REditCommand.EditReviewDescriptor descriptor;

    public EditReviewDescriptorBuilder() {
        descriptor = new REditCommand.EditReviewDescriptor();
    }

    public EditReviewDescriptorBuilder(REditCommand.EditReviewDescriptor descriptor) {
        this.descriptor = new REditCommand.EditReviewDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditReviewDescriptor} with fields containing {@code review}'s details
     */
    public EditReviewDescriptorBuilder(Review review) {
        descriptor = new REditCommand.EditReviewDescriptor();
        descriptor.setDate(review.getDate());
        descriptor.setContent(review.getContent());
        descriptor.setRating(review.getRating());
        descriptor.setTags(review.getTags());
    }

    /**
     * Sets the {@code Date} of the {@code EditReviewDescriptor} that we are building.
     */
    public EditReviewDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Content} of the {@code EditReviewDescriptor} that we are building.
     */
    public EditReviewDescriptorBuilder withContent(String content) {
        descriptor.setContent(new Content(content));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditReviewDescriptor} that we are building.
     */
    public EditReviewDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditReviewDescriptor}
     * that we are building.
     */
    public EditReviewDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public REditCommand.EditReviewDescriptor build() {
        return descriptor;
    }
}
