package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Fields;

/**
 * Jackson-friendly version of {@link Fields}.
 */
class JsonAdaptedFields {

    private final List<JsonAdaptedField> fieldList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFields} with the given {@code fieldList}.
     */
    @JsonCreator
    public JsonAdaptedFields(@JsonProperty("fields") List<JsonAdaptedField> fieldList) {
        if (fieldList != null) {
            this.fieldList.addAll(fieldList);
        }
    }

    /**
     * Converts a given {@code Fields} into this class for Jackson use.
     */
    public JsonAdaptedFields(Fields fields) {
        if (fields != null) {
            fieldList.addAll(fields.toList().stream()
                    .map(JsonAdaptedField::new).collect(Collectors.toList()));
        }
    }

    /**
     * Converts this Jackson-friendly adapted fields object into the model's {@code Fields} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted fields.
     */
    public Fields toModelType() throws IllegalValueException {
        Fields fields = new Fields();

        // Exception handling is not supported in Java streams.
        for (JsonAdaptedField jsonAdaptedField : fieldList) {
            fields.addField(jsonAdaptedField.toModelType());
        }
        return fields;
    }
}
