package seedu.address.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.AbstractAttribute;
import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Description;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.Phone;

class JsonAdaptedAbstractAttribute {

    Map<String, Object> data = new HashMap<>();

    @JsonCreator
    public JsonAdaptedAbstractAttribute(@JsonProperty("data") Map<String, Object> data) {
        this.data.putAll(data);
    }

    public JsonAdaptedAbstractAttribute(Attribute attribute) {
        data.putAll(attribute.toSaveableData());
    }

    public Attribute toModelType() throws IllegalValueException {
        final String modelTypeName = (String) data.get("type");
        final Object modelValue = data.get("content");
        final int modelDisplayFormat = (int) data.get("display_format");
        final int modelStyleFormat = (int) data.get("style_format");

        Attribute modelAttribute;
        //TODO class cast exception, generics
        switch (modelTypeName) {
            case Address.TYPE:
                if (!Address.isValidAddress((String) modelValue)) {
                    throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
                }

                modelAttribute = new Address((String) modelValue);
                break;
            case Description.TYPE:
                modelAttribute = new Description((String) modelValue);
                break;
            case Email.TYPE:
                if (!Email.isValidEmail((String) modelValue)) {
                    throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
                }

                modelAttribute = new Email((String) modelValue);
                break;
            case Name.TYPE:
                if (!Name.isValidName((String) modelValue)) {
                    throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
                }

                modelAttribute = new Address((String) modelValue);
                break;
            case Phone.TYPE:
                if (!Phone.isValidPhone((String) modelValue)) {
                    throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
                }

                modelAttribute = new Address((String) modelValue);
                break;
            default:
                modelAttribute = new AbstractAttribute(modelTypeName, modelValue,
                        modelDisplayFormat, modelStyleFormat) {
                };
        }
        return modelAttribute;
    }
}
