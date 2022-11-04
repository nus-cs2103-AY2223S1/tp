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

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attribute's %s field is missing!";
    private static final String KEY_TYPE = "type";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DISPLAY_FORMAT = "display_format";
    private static final String KEY_STYLE_FORMAT = "style_format";

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

    /**
     * Converts this Jackson-friendly adapted attribute object into the model's {@code Attribute}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *         attribute.
     */
    public Attribute<?> toModelType() throws IllegalValueException {
        assert data != null;
        if (!data.containsKey(KEY_TYPE) || !data.containsKey(KEY_CONTENT)
            || !data.containsKey(KEY_DISPLAY_FORMAT) || !data.containsKey(KEY_STYLE_FORMAT)) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }

        final String modelTypeName = (String) data.get(KEY_TYPE);
        final Object modelValue = data.get(KEY_CONTENT);
        final int modelDisplayFormat = (int) data.get(KEY_DISPLAY_FORMAT);
        final int modelStyleFormat = (int) data.get(KEY_STYLE_FORMAT);

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
