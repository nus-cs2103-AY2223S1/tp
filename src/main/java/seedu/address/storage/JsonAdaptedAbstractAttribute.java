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

/**
 * Jackson-friendly version of {@link Attribute}.
 */
class JsonAdaptedAbstractAttribute {

    public static final String CORRUPTED_FIELD_MESSAGE_FORMAT = "Attribute data is corrupted!";

    private final Map<String, Object> data = new HashMap<>();

    /**
     * Constructs a {@code JsonAdaptedAbstractAttribute} with the given attribute details.
     */
    @JsonCreator
    public JsonAdaptedAbstractAttribute(@JsonProperty("data") Map<String, Object> data) {
        if (data != null) {
            this.data.putAll(data);
        }
    }

    /**
     * Converts a given {@code Attribute} into this class for Jackson use.
     */
    public JsonAdaptedAbstractAttribute(Attribute<?> attribute) {
        if (attribute != null) {
            data.putAll(attribute.toSaveableData());
        }
    }

    public static boolean isSaveableDataFormat(Map<String, Object> data) {
        return data.containsKey(AbstractAttribute.SAVE_KEY_TYPE_NAME)
                && data.containsKey(AbstractAttribute.SAVE_KEY_VALUE)
                && data.containsKey(AbstractAttribute.SAVE_KEY_DISPLAY_FORMAT)
                && data.containsKey(AbstractAttribute.SAVE_KEY_STYLE_FORMAT);
    }

    /**
     * Converts this Jackson-friendly adapted attribute object into the model's {@code Attribute}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *         attribute.
     */
    public Attribute<?> toModelType() throws IllegalValueException {
        assert data != null;
        if (!isSaveableDataFormat(data)) {
            throw new IllegalValueException(CORRUPTED_FIELD_MESSAGE_FORMAT);
        }

        final String modelTypeName = (String) data.get(AbstractAttribute.SAVE_KEY_TYPE_NAME);
        final Object modelValue = data.get(AbstractAttribute.SAVE_KEY_VALUE);
        final int modelDisplayFormat = (int) data.get(AbstractAttribute.SAVE_KEY_DISPLAY_FORMAT);
        final int modelStyleFormat = (int) data.get(AbstractAttribute.SAVE_KEY_STYLE_FORMAT);

        Attribute<?> modelAttribute;
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

            modelAttribute = new Name((String) modelValue);
            break;
        case Phone.TYPE:
            if (!Phone.isValidPhone((String) modelValue)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }

            modelAttribute = new Phone((String) modelValue);
            break;
        default:
            modelAttribute = new AbstractAttribute<Object>(modelTypeName, modelValue,
                modelDisplayFormat, modelStyleFormat) {};
        }
        return modelAttribute;
    }
}
