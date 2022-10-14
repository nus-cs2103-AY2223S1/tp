package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.message.Message;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Message}.
 */
class JsonAdaptedMessage {

    private final String message;

    /**
     * Constructs a {@code JsonAdaptedMessage} with the given {@code message}.
     */
    @JsonCreator
    public JsonAdaptedMessage(String message) {
        this.message = message;
    }

    /**
     * Converts a given {@code Message} into this class for Jackson use.
     */
    public JsonAdaptedMessage(Message source) {
        message = source.getMessage();
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Message} object.
     *
     */
    public Message toModelType() {
        return new Message(message);
    }

}
