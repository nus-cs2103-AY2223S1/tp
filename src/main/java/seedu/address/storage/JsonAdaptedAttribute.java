package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.attribute.Attribute;

import java.util.Map;

class JsonAdaptedAttribute {

    private final

    @JsonCreator
    public JsonAdaptedAttribute(@JsonProperty("type") String typeName, @JsonProperty("content") Object value,
                                @JsonProperty("display_format") int displayFormat,
                                @JsonProperty("style_format") int styleFormat) {

    }

    public JsonAdaptedAttribute(Attribute attribute) {
        data = attribute.toSaveableData();
    }

    public Attribute toModelType() {
        return new Attribute()
    }
}
