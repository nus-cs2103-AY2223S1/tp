package coydir.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import coydir.commons.exceptions.IllegalValueException;
import coydir.model.person.Rating;
import coydir.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Rating}.
 */
public class JsonAdaptedRating {

    private final String value;
    private final String timestamp;

    /**
     * Constructs a {@code JsonAdaptedRating} with the given {@code rating} and {@code timestamp}.
     */
    @JsonCreator
    public JsonAdaptedRating(@JsonProperty("rating") String value, @JsonProperty("timestamp") String timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    /**
     * Converts a given {@code Rating} into this class for Jackson use.
     */
    public JsonAdaptedRating(Rating source) {
        this.value = String.valueOf(source.value);
        this.timestamp = source.timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Converts this Jackson-friendly adapted Rating object into the model's {@code Rating} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Rating.
     */
    public Rating toModelType() throws IllegalValueException {
        if (!Rating.isValidRating(value)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Rating(value, timestamp);
    }

}
