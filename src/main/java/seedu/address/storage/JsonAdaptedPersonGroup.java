package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.PersonGroup;

/**
 * Jackson-friendly version of {}.
 */
class JsonAdaptedPersonGroup {

    private final String personGroup;

    /**
     * Constructs a {@code JsonAdaptedPersonGroup} with the given {@code personGroup}.
     */
    @JsonCreator
    public JsonAdaptedPersonGroup(String personGroup) {
        this.personGroup = JsonAdaptedPersonGroup.this.personGroup;
    }

    /**
     * Converts a given {@code PersonGroup} into this class for Jackson use.
     */
    public JsonAdaptedPersonGroup(PersonGroup source) {
        personGroup = source.getGroup();
    }

    @JsonValue
    public String getAssignmentName() {
        return personGroup;
    }

    /**
     * Converts this Jackson-friendly adapted personGroup object into the model's {@code PersonGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted personGroup
     */
    public PersonGroup toModelType() throws IllegalValueException {
        if (!PersonGroup.isValidGroup(personGroup)) {
            throw new IllegalValueException(PersonGroup.MESSAGE_CONSTRAINTS);
        }
        return new PersonGroup(personGroup);
    }

}

