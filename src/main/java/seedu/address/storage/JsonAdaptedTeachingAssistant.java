package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ta.TeachingAssistant;
import seedu.address.model.ta.TeachingAssistantId;
import seedu.address.model.ta.TeachingAssistantName;

/**
 * Jackson-friendly version of {@link TeachingAssistant}.
 */
public class JsonAdaptedTeachingAssistant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Teaching Assistant's %s field is missing!";

    private final String name;
    private final String id;

    /**
     * Constructs a {@code JsonAdaptedTeachingAssistant} with the given teaching assistant details.
     */
    @JsonCreator
    public JsonAdaptedTeachingAssistant(@JsonProperty("name") String name, @JsonProperty("id") String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Converts a given {@code TeachingAssistant} into this class for Jackson use.
     */
    public JsonAdaptedTeachingAssistant(TeachingAssistant source) {
        name = source.getName().fullName;
        id = source.getId().id;
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's {@code TeachingAssistant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted teaching assistant.
     */
    public TeachingAssistant toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TeachingAssistant.class.getSimpleName()));
        }
        if (!TeachingAssistantName.isValidName(name)) {
            throw new IllegalValueException(TeachingAssistantName.MESSAGE_CONSTRAINTS);
        }
        final TeachingAssistantName modelName = new TeachingAssistantName(name);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TeachingAssistantId.class.getSimpleName()));
        }
        if (!TeachingAssistantId.isValidId(id)) {
            throw new IllegalValueException(TeachingAssistantId.MESSAGE_CONSTRAINTS);
        }
        final TeachingAssistantId modelId = new TeachingAssistantId(id);

        return new TeachingAssistant(modelName, modelId);
    }

}
