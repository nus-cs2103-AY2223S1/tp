package seedu.address.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javafx.collections.ObservableMap;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;
import seedu.address.model.tag.UniqueTagList;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTagTypeMap {

    private final Map<JsonAdaptedTagType, JsonAdaptedTagList> tagTypeMap = new HashMap<>();

    /**
     * Constructs a {@code JsonAdaptedTagTypeMap} with the given {@code tagTypeMap}.
     */
    @JsonCreator
    public JsonAdaptedTagTypeMap(Map<JsonAdaptedTagType, JsonAdaptedTagList> tagTypeMap) {
        this.tagTypeMap.putAll(tagTypeMap);
    }

    /**
     * Converts a given {@code TagTypeMap} into this class for Jackson use.
     */
    public JsonAdaptedTagTypeMap(ObservableMap<TagType, UniqueTagList> tagTypeMap) {
        for (TagType key: tagTypeMap.keySet()) {
            this.tagTypeMap.put(new JsonAdaptedTagType(key),
                    new JsonAdaptedTagList(tagTypeMap.get(key)));
        }
    }

    @JsonValue
    public Map<JsonAdaptedTagType, JsonAdaptedTagList> getTagTypeMap() {
        return tagTypeMap;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Map<TagType, UniqueTagList> toModelType() throws IllegalValueException {
        Map<TagType, UniqueTagList> map = new HashMap<>();
        for (JsonAdaptedTagType key: tagTypeMap.keySet()) {
            map.put(key.toModelType(), tagTypeMap.get(key).toModelType());
        }
        return map;
    }

}
