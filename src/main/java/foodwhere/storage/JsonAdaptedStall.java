package foodwhere.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.logic.parser.ParserUtil;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;

/**
 * Jackson-friendly version of {@link Stall}.
 */
class JsonAdaptedStall {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Stall's %s field is missing!";

    private final String name;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedReview> reviews = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStall} with the given stall details.
     */
    @JsonCreator
    public JsonAdaptedStall(@JsonProperty("name") String name,
                            @JsonProperty("address") String address,
                            @JsonProperty("tags") List<JsonAdaptedTag> tags,
                            @JsonProperty("reviews") List<JsonAdaptedReview> reviews) {
        this.name = name;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (reviews != null) {
            this.reviews.addAll(reviews);
        }
    }

    /**
     * Converts a given {@code Stall} into this class for Jackson use.
     */
    public JsonAdaptedStall(Stall source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        reviews.addAll(source.getReviews().stream()
                .map(JsonAdaptedReview::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted stall object into the model's {@code Stall} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stall.
     */
    public Stall toModelType() throws IllegalValueException {
        final List<Tag> stallTags = new ArrayList<>();
        for (JsonAdaptedTag tag : this.tags) {
            stallTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        final Name modelName = ParserUtil.parseName(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(stallTags);

        return new Stall(modelName, modelAddress, modelTags, new HashSet<>(getModelReviews()));
    }

    /**
     * Converts the reviews in this Jackson-friendly adapted stall object into the model's {@code Review} objects.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stall.
     */
    public List<Review> getModelReviews() throws IllegalValueException {
        final List<Review> modelReviews = new ArrayList<>();

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        final Name modelStallName = ParserUtil.parseName(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelStallAddress = new Address(address);

        for (JsonAdaptedReview review : reviews) {
            modelReviews.add(review.toModelType(modelStallName, modelStallAddress));
        }
        return modelReviews;
    }
}
