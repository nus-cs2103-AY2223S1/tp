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
import foodwhere.model.stall.Address;
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
    public JsonAdaptedReview(Stall source) {
        this.date = "";
        this.content = source.getAddress().toString(); // proxy - to change
        details.addAll(source.getDetails().stream()
                .map(JsonAdaptedDetail::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted review object into the model's {@code Review} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stall.
     */
    public Stall toModelType(Name modelName) throws IllegalValueException {
        if (modelName == null) {
            throw new IllegalValueException(String.format(JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }

        final List<Detail> reviewDetails = new ArrayList<>();
        for (JsonAdaptedDetail detail : details) {
            reviewDetails.add(detail.toModelType());
        }

        if (content == null) {
            throw new IllegalValueException(String.format(JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (Address.isValidAddress(content)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Address modelContent = new Address(content);
        final Set<Detail> modelDetails = new HashSet<>(reviewDetails);

        return new Stall(modelName, modelContent, modelDetails);
    }

}
