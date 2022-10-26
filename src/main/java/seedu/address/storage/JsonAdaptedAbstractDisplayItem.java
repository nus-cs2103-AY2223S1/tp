package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

abstract class JsonAdaptedAbstractDisplayItem {

    private final String name;
    private final List<JsonAdaptedAttribute> attributes = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    protected JsonAdaptedAbstractDisplayItem(String name, List<JsonAdaptedAttribute> attributes,
                                             List<JsonAdaptedTag> tags) {
        this.name = name;
        this.attributes.addAll(attributes);
        this.tags.addAll(tags);
    }

    protected String getName() {
        return name;
    }

    protected List<JsonAdaptedAttribute> getAttributes() {
        return attributes;
    }

    protected List<JsonAdaptedTag> getTags() {
        return tags;
    }
}
