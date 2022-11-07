package seedu.clinkedin.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTagType {

    private final String tagType;
    private final String prefix;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTagType(@JsonProperty("tagType") String tagType, @JsonProperty("prefix") String prefix) {
        this.tagType = tagType;
        this.prefix = prefix;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTagType(TagType source) {
        this.tagType = source.tagType;
        this.prefix = source.getPrefix().getPrefix();
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
        return new TagType(tagType, new Prefix(prefix));
    }
}
