package coydir.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import coydir.commons.exceptions.IllegalValueException;
import coydir.model.person.Rating;
import coydir.model.tag.Tag;

public class JsonAdaptedRating {
    private final String value;
    private final String timestamp;

    /**
     * Constructs a {@code JsonAdaptedLeave} with the given {@code startDate} and {@code endDate}.
     */
    @JsonCreator
    public JsonAdaptedRating(@JsonProperty("rating") String value, @JsonProperty("timestamp") String timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    /**
     * Converts a given {@code Leave} into this class for Jackson use.
     */
    public JsonAdaptedRating(Rating source) {
        this.value = String.valueOf(source.value);
        this.timestamp = String.valueOf(source.timestamp);
    }


    /**
     * Converts this Jackson-friendly adapted Leave object into the model's {@code Leave} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Leave.
     */
    public Rating toModelType() throws IllegalValueException {
        if (!Rating.isValidRating(value)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Rating(value, timestamp);
    }
    
}
