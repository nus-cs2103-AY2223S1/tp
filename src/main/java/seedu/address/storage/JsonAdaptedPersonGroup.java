package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.PersonGroup;

/**
 * Jackson-friendly version of {@link PersonGroup}.
 */
class JsonAdaptedPersonGroup {

    private final String personGroupname;

    /**
     * Constructs a {@code JsonAdaptedPersonGroup} with the given {@code personGroup}.
     */
    @JsonCreator
    public JsonAdaptedPersonGroup(String personGroup) {
        this.personGroupname = personGroup;
    }

    /**
     * Converts a given {@code PersonGroup} into this class for Jackson use.
     */
    public JsonAdaptedPersonGroup(PersonGroup source) {
        personGroupname = source.getGroupName();
    }

    @JsonValue
    public String getPersonGroup() {
        return personGroupname;
    }

    /**
     * Converts this Jackson-friendly adapted personGroup object into the model's {@code PersonGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted personGroup
     */
    public PersonGroup toModelType() throws IllegalValueException {
        if (!PersonGroup.isValidGroup(personGroupname)) {
            throw new IllegalValueException(PersonGroup.MESSAGE_CONSTRAINTS);
        }
        return new PersonGroup(personGroupname);
    }

}

