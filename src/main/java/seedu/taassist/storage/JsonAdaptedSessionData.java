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

    public static final String MESSAGE_MISSING_NAME = "Session's name field is missing!";
    public static final String MESSAGE_MISSING_GRADE = "Session's grade field is missing!";
    public static final String MESSAGE_INVALID_GRADE = "Session's grade field is invalid!";

    @JsonProperty("session")
    private final String sessionName;
    private final Double grade;

    /**
     * Constructs a {@code JsonAdaptedSessionData} with the given {@code session} and list
     * of {@code StudentSessionData}.
     */
    @JsonCreator
    public JsonAdaptedSessionData(@JsonProperty("session") String sessionName,
                                  @JsonProperty("grade") Double grade) {
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
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public SessionData toModelType() throws IllegalValueException {
        if (sessionName == null) {
            throw new IllegalValueException(MESSAGE_MISSING_NAME);
        }
        if (!Session.isValidSessionName(sessionName)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }
        if (grade == null) {
            throw new IllegalValueException(MESSAGE_MISSING_GRADE);
        }
        if (!SessionData.isValidGrade(grade)) {
            throw new IllegalValueException(MESSAGE_INVALID_GRADE);
        }
        return new SessionData(new Session(sessionName), grade);
    }

}
