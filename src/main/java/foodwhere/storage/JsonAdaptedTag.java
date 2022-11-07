package foodwhere.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.commons.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String tag;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tag}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tag) {
        this.tag = tag;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tag = source.tag;
    }

    @JsonValue
    public String getTag() {
        return tag;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTag(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(tag);
    }

}
