package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.portfolio.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    private final String noteName;

    /**
     * Constructs a {@code JsonAdaptedPlan} with the given {@code planName}.
     */
    @JsonCreator
    public JsonAdaptedNote(String noteName) {
        this.noteName = noteName;
    }

    /**
     * Converts a given {@code Plan} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        noteName = source.value;
    }

    @JsonValue
    public String getNoteName() {
        return noteName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Plan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted plan.
     */
    public Note toModelType() throws IllegalValueException {
        if (!Note.isValidNote(noteName)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(noteName);
    }

}
