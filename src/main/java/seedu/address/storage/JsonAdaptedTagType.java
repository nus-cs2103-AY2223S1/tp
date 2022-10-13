package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTagType {

    private final String tagType;
    private final Prefix prefix;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTagType(String tagType, Prefix prefix) {
        this.tagType = tagType;
        this.prefix = prefix;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTagType(TagType source) {
        this.tagType = source.tagType;
        this.prefix = source.p;
    }

    @JsonValue
    public String getTagType() {
        return tagType;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public TagType toModelType() throws IllegalValueException {
        if (!TagType.isValidTagType(tagType)) {
            throw new IllegalValueException(TagType.MESSAGE_CONSTRAINTS);
        }
        return new TagType(tagType, prefix);
    }
}
