package seedu.clinkedin.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javafx.collections.ObservableMap;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedPrefixMap {

    private final Map<String, JsonAdaptedTagType> prefixMap = new HashMap<>();

    /**
     * Constructs a {@code JsonAdaptedTagTypeMap} with the given {@code tagTypeMap}.
     */
    @JsonCreator
    public JsonAdaptedPrefixMap(Map<String, JsonAdaptedTagType> prefixMap) {
        this.prefixMap.putAll(prefixMap);
    }

    /**
     * Converts a given {@code TagTypeMap} into this class for Jackson use.
     */
    public JsonAdaptedPrefixMap(ObservableMap<Prefix, TagType> prefixMap) {
        for (Prefix prefix: prefixMap.keySet()) {
            this.prefixMap.put(prefix.getPrefix(),
                    new JsonAdaptedTagType(prefixMap.get(prefix)));
        }
    }

    @JsonValue
    public Map<String, JsonAdaptedTagType> getPrefixMap() {
        return prefixMap;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Map<Prefix, TagType> toModelType() throws IllegalValueException {
        Map<Prefix, TagType> map = new HashMap<>();
        for (String prefix: prefixMap.keySet()) {
            map.put(new Prefix(prefix), prefixMap.get(prefix).toModelType());
        }
        return map;
    }

}
