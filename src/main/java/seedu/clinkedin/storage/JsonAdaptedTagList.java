package seedu.clinkedin.storage;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.UniqueTagList;


/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTagList {

    private final List<JsonAdaptedTag> tags;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTagList(List<JsonAdaptedTag> tags) {
        this.tags = tags;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTagList(UniqueTagList source) {
        this.tags = source.toStream().map(JsonAdaptedTag::new).collect(Collectors.toList());
    }

    @JsonValue
    public List<JsonAdaptedTag> getTagList() {
        return tags;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public UniqueTagList toModelType() throws IllegalValueException {
        UniqueTagList list = new UniqueTagList();
        for (JsonAdaptedTag tag : tags) {
            list.add(tag.toModelType());
        }
        return list;
    }
}
