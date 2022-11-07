package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.Session;

/**
 * Jackson-friendly version of {@link Session}.
 */
public class JsonAdaptedSession {
    private final String sessionDesc;
    /**
     * Constructs a {@code JsonAdaptedSession} with the given {@code sessionDesc}.
     */
    @JsonCreator
    public JsonAdaptedSession(String sessionDesc) {
        this.sessionDesc = sessionDesc;
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        sessionDesc = source.toString();
    }

    @JsonValue
    public String getSessionDesc() {
        return sessionDesc;
    }
    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Session} object.
     */
    public Session toModelType() {
        return new Session(sessionDesc);
    }
}
