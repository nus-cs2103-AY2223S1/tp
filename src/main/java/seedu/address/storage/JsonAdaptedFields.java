package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Fields;

class JsonAdaptedFields {

    private final List<JsonAdaptedField> fieldList = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedFields(@JsonProperty("fields") List<JsonAdaptedField> fieldList) {
        this.fieldList.addAll(fieldList);
    }

    public JsonAdaptedFields(Fields fields) {
        fieldList.addAll(fields.toList().stream()
                .map(JsonAdaptedField::new).collect(Collectors.toList()));
    }

    public Fields toModelType() throws IllegalValueException {
        Fields fields = new Fields();
        for (JsonAdaptedField jsonAdaptedField : fieldList) {
            fields.addField(jsonAdaptedField.toModelType());
        }
        return fields;
    }
}
