package foodwhere.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.detail.Detail;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Review;
import foodwhere.model.review.StallName;
import foodwhere.model.stall.Name;
import foodwhere.model.stall.Stall;

/**
 * Jackson-friendly version of {@link Stall}.
 */
class JsonAdaptedReview {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Review's %s field is missing!";

    private final String date;
    private final String content;
    private final List<JsonAdaptedDetail> details = new ArrayList<>();



    /**
     * Constructs a {@code JsonAdaptedReview} with the given stall details.
     */
    @JsonCreator
    public JsonAdaptedReview(@JsonProperty("date") String date,
                             @JsonProperty("content") String content,
                             @JsonProperty("details") List<JsonAdaptedDetail> details) {
        this.date = date;
        this.content = content;
        if (details != null) {
            this.details.addAll(details);
        }
    }

    /**
     * Converts a given {@code Review} into this class for Jackson use.
     */
    public JsonAdaptedReview(Review source) {
        this.date = source.getDate().value;
        this.content = source.getContent().value;
        details.addAll(source.getDetails().stream()
                .map(JsonAdaptedDetail::new)
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

        final List<Detail> reviewDetails = new ArrayList<>();
        for (JsonAdaptedDetail detail : details) {
            reviewDetails.add(detail.toModelType());
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
        final Content modelContent = new Content(content); // to add later

        final Set<Detail> modelDetails = new HashSet<>(reviewDetails);

        return new Review(new StallName(modelName.fullName), modelDate, modelContent, modelDetails);
    }

}
