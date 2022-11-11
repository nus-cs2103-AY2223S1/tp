package swift.storage;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import swift.commons.exceptions.IllegalValueException;
import swift.model.bridge.PersonTaskBridge;

/**
 * Jackson-friendly version of {@link PersonTaskBridge}.
 */
class JsonAdaptedBridge {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Bridge's %s field is missing!";

    private final String personId;
    private final String taskId;

    /**
     * Constructs a {@code JsonAdaptedBridge} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedBridge(@JsonProperty("personId") String personId, @JsonProperty("taskId") String taskId) {
        this.personId = personId;
        this.taskId = taskId;
    }

    /**
     * Converts a given {@code PersonTaskBridge} into this class for Jackson use.
     */
    public JsonAdaptedBridge(PersonTaskBridge source) {
        personId = source.getPersonId().toString();
        taskId = source.getTaskId().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person-task bridge object into the
     * model's
     * {@code PersonTaskBridge} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person-task bridge.
     */
    public PersonTaskBridge toModelType() throws IllegalValueException {
        if (personId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName()));
        }
        try {
            UUID.fromString(personId);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
        final UUID modelPersonId = UUID.fromString(personId);

        if (taskId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName()));
        }
        try {
            UUID.fromString(taskId);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
        final UUID modelTaskId = UUID.fromString(taskId);

        return new PersonTaskBridge(modelPersonId, modelTaskId);
    }

}
