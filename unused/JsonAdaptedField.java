// @@author autumn-sonata

package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.Field;

/**
 * Jackson-friendly version of {@link Field}.
 */
class JsonAdaptedField {

    private final String name;
    private final String value;

    /**
     * Constructs a {@code JsonAdaptedField} with the given field details.
     */
    @JsonCreator
    public JsonAdaptedField(@JsonProperty("name") String name, @JsonProperty("value") String value) {
        this.name = name == null ? "" : name;
        this.value = value == null ? "" : value;
    }

    public JsonAdaptedField(Field field) {
        assert field != null;
        name = field.name;
        value = field.value;
    }

    /**
     * Converts this Jackson-friendly adapted field object into the model's {@code Field} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted field.
     */
    public Field toModelType() throws IllegalValueException {
        if (!Field.isValidName(name) || !Field.isValidField(value)) {
            throw new IllegalValueException(Field.MESSAGE_CONSTRAINTS);
        }

        return new Field(name, value);
    }
}
