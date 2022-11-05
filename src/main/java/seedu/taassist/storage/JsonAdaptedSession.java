package seedu.taassist.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.session.Date;
import seedu.taassist.model.session.Session;

/**
 * Json-friendly version of {@link Session}.
 */
class JsonAdaptedSession {

    public static final String MESSAGE_MISSING_NAME = "Session's name field is missing!";
    public static final String MESSAGE_MISSING_DATE = "Session's date field is missing!";

    @JsonProperty("name")
    private final String sessionName;

    @JsonProperty("date")
    private final LocalDate date;

    /**
     * Contructs a {@code JsonAdaptedSession} with the given {@code sessionName}.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("name") String sessionName, @JsonProperty("date") LocalDate date) {

        this.sessionName = sessionName;
        this.date = date;
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {

        this.sessionName = source.getSessionName();
        this.date = source.getDate().getValue();
    }

    /**
     * Converts this Json-friendly adapted session object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted session.
     */
    public Session toModelType() throws IllegalValueException {
        if (sessionName == null) {
            throw new IllegalValueException(MESSAGE_MISSING_NAME);
        }
        if (!Session.isValidSessionName(sessionName)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }
        if (date == null) {
            throw new IllegalValueException(MESSAGE_MISSING_DATE);
        }
        return new Session(sessionName, new Date(date));
    }
}
