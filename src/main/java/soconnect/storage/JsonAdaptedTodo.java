package soconnect.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import soconnect.commons.exceptions.IllegalValueException;
import soconnect.model.todo.Todo;

/**
 * Jackson-friendly version of {@link Todo}.
 */
class JsonAdaptedTodo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Todo's %s field is missing!";

    private final String description;

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("description") String description) {
        this.description = description;
    }

    /**
     * Converts a given {@code Todo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(Todo source) {
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted todo object into the model's {@code Todo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted todo.
     */
    public Todo toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        return new Todo(description);
    }

}
