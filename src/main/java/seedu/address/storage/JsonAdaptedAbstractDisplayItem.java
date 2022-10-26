package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.List;

abstract class JsonAdaptedAbstractDisplayItem {

    private final String name;
    private final List<JsonAdaptedAbstractAttribute> attributes = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();


    protected JsonAdaptedAbstractDisplayItem(String name, List<JsonAdaptedAbstractAttribute> attributes,
                                             List<JsonAdaptedTag> tags) {
        this.name = name;
        this.attributes.addAll(attributes);
        this.tags.addAll(tags);
    }

    protected String getName() {
        return name;
    }

    protected List<JsonAdaptedAbstractAttribute> getAttributes() {
        return attributes;
    }

    protected List<JsonAdaptedTag> getTags() {
        return tags;
    }
}
