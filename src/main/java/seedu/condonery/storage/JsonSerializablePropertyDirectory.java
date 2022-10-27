package seedu.condonery.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.condonery.commons.exceptions.IllegalValueException;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

/**
 * An Immutable PropertyDirectory that is serializable to JSON format.
 */
@JsonRootName(value = "propertyDirectory")
class JsonSerializablePropertyDirectory {

    public static final String MESSAGE_DUPLICATE_PROPERTY = "Properties list contains duplicate property(s).";

    private final List<JsonAdaptedProperty> properties = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePropertyDirectory} with the given properties.
     */
    @JsonCreator
    public JsonSerializablePropertyDirectory(@JsonProperty("properties") List<JsonAdaptedProperty> properties) {
        this.properties.addAll(properties);
    }

    /**
     * Converts a given {@code ReadOnlyPropertyDirectory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePropertyDirectory}.
     */
    public JsonSerializablePropertyDirectory(ReadOnlyPropertyDirectory source) {
        properties.addAll(source.getPropertyList().stream().map(JsonAdaptedProperty::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PropertyDirectory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PropertyDirectory toModelType() throws IllegalValueException {
        PropertyDirectory propertyDirectory = new PropertyDirectory();
        for (JsonAdaptedProperty jsonAdaptedProperty : properties) {
            Property property = jsonAdaptedProperty.toModelType();
            if (propertyDirectory.hasProperty(property)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROPERTY);
            }
            propertyDirectory.addProperty(property);
        }
        return propertyDirectory;
    }

}
