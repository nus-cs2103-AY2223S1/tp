package seedu.watson.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.watson.commons.exceptions.IllegalValueException;
import seedu.watson.model.person.subject.Subject;

/**
 * Jackson-friendly version of {@link Subject}.
 */
public class JsonAdaptedSubject {

    private final String subjectName;

    /**
     * Constructs a {@code JsonAdaptedSubject} with the given {@code Subject}.
     */
    @JsonCreator
    public JsonAdaptedSubject(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Converts a given {@code Subject} into this class for Jackson use.
     */
    public JsonAdaptedSubject(Subject source) {
        subjectName = source.getSubjectName();
    }

    @JsonValue
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Converts this Jackson-friendly adapted subject object into the model's {@code Subject} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted subject.
     */
    public Subject toModelType() throws IllegalValueException {
        if (!Subject.isValidSubject(subjectName)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(subjectName);
    }

}
