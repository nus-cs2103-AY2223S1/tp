package foodwhere.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;
import foodwhere.model.review.Review;

/**
 * Jackson-friendly version of {@link Review}.
 */
class JsonAdaptedReview {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Review's %s field is missing!";

    private final String date;
    private final String content;
    private final Integer rating;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedReview} with the given stall details.
     */
    @JsonCreator
    public JsonAdaptedReview(@JsonProperty("date") String date,
                             @JsonProperty("content") String content,
                             @JsonProperty("rating") Integer rating,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.date = date;
        this.content = content;
        this.rating = rating;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Review} into this class for Jackson use.
     */
    public JsonAdaptedReview(Review source) {
        this.date = source.getDate().value;
        this.content = source.getContent().value;
        this.rating = source.getRating().value;
        this.tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted review object into the model's {@code Review} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stall.
     */
    public Review toModelType(Name modelName) throws IllegalValueException {
        if (modelName == null) {
            throw new IllegalValueException(String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }

        final List<Tag> reviewTags = new ArrayList<>();
        for (JsonAdaptedTag tag : this.tags) {
            reviewTags.add(tag.toModelType());
        }

        if (date == null) {
            throw new IllegalValueException(String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (content == null) {
            throw new IllegalValueException(String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT,
                    Content.class.getSimpleName()));
        }
        if (!Content.isValidContent(content)) {
            throw new IllegalValueException(Content.MESSAGE_CONSTRAINTS);
        }
        final Content modelContent = new Content(content);

        if (rating == null) {
            throw new IllegalValueException(String.format(JsonAdaptedReview.MISSING_FIELD_MESSAGE_FORMAT,
                    Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Content.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        final Set<Tag> modelTags = new HashSet<>(reviewTags);

        return new Review(modelName, modelDate, modelContent, modelRating, modelTags);
    }

}
