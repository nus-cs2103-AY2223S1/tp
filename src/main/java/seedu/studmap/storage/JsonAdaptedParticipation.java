package seedu.studmap.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.model.student.Participation;

/**
 * Jackson-friendly version of {@link Participation}.
 */
class JsonAdaptedParticipation {

    private final String participationComponent;

    /**
     * Constructs a {@code JsonAdaptedParticipation} with the given {@code participationComponent}.
     */
    @JsonCreator
    public JsonAdaptedParticipation(String participationComponent) {
        this.participationComponent = participationComponent;
    }

    /**
     * Converts a given {@code Participation} into this class for Jackson use.
     */
    public JsonAdaptedParticipation(Participation source) {
        participationComponent = source.getString();
    }

    @JsonValue
    public String getClassName() {
        return participationComponent;
    }

    /**
     * Converts this Jackson-friendly adapted participation object into the model's {@code Participation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participation.
     */
    public Participation toModelType() throws IllegalValueException {
        String[] values = participationComponent.split(":");
        Participation.Status status;
        if (values.length != 2 || !Participation.isValidParticipationName(values[0])) {
            throw new IllegalValueException(Participation.MESSAGE_CONSTRAINTS);
        }

        try {
            status = Participation.Status.fromString(values[1]);
        } catch (IllegalArgumentException iae) {
            throw new IllegalValueException(iae.getMessage());
        }

        return new Participation(values[0], status);
    }

}
