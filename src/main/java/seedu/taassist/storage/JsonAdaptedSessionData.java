package seedu.taassist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.SessionData;

/**
 * Json-friendly version of {@link SessionData}.
 */
class JsonAdaptedSessionData {

    public static final String MISSING_NAME_MESSAGE = "Session's name field is missing!";

    @JsonProperty("session")
    private final String sessionName;
    private final double grade;

    /**
     * Constructs a {@code JsonAdaptedSessionData} with the given {@code session} and list
     * of {@code StudentSessionData}.
     */
    @JsonCreator
    public JsonAdaptedSessionData(@JsonProperty("session") String sessionName,
                                  @JsonProperty("grade") double grade) {
        this.sessionName = sessionName;
        this.grade = grade;
    }

    /**
     * Converts a given {@code SessionData} into this class for Jackson use.
     */
    public JsonAdaptedSessionData(SessionData source) {
        sessionName = source.getSessionName();
        grade = source.getGrade();
    }

    /**
     * Converts this Json-friendly adapted module class object into the model's {@code SessionData} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SessionData toModelType() throws IllegalValueException {
        if (sessionName == null) {
            throw new IllegalValueException(MISSING_NAME_MESSAGE);
        }
        if (!Session.isValidSessionName(sessionName)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }
        return new SessionData(new Session(sessionName), grade);
    }

}
