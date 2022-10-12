package gim.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import gim.commons.exceptions.IllegalValueException;
import gim.model.tag.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Date source) {
        tagName = source.toString();
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Date toModelType() throws IllegalValueException {
        if (!Date.isValidDate(tagName)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(tagName);
    }

}
