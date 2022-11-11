package seedu.address.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attribute.AbstractAttribute;
import seedu.address.model.attribute.Attribute;

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

        try {
            Attribute<?> modelAttribute =
                    ParserUtil.parseAttribute(modelTypeName, modelValue, modelDisplayFormat, modelStyleFormat);
            return modelAttribute;
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
