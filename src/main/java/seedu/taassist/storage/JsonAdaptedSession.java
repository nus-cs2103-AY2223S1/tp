package seedu.taassist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.session.Session;

/**
 * Json-friendly version of {@link Session}.
 */
class JsonAdaptedSession {

    @JsonProperty("name")
    private final String sessionName;

    /**
     * Contructs a {@code JsonAdaptedSession} with the given {@code sessionName}.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("name") String sessionName) {
        this.sessionName = sessionName;
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        this.sessionName = source.getSessionName();
    }

    /**
     * Converts this Json-friendly adapted session object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted session.
     */
    public Session toModelType() throws IllegalValueException {
        if (!Session.isValidSessionName(sessionName)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }
        return new Session(sessionName);
    }
}
