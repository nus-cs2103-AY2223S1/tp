package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DateTime;

/**
 * Jackson-friendly version of {@link DateTime}.
 */
class JsonAdaptedDateTime {

    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedDateTime} with the given {@code DateTime}.
     */
    @JsonCreator
    public JsonAdaptedDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDateTime(DateTime source) {
        dateTime = source.getString();
    }

    @JsonValue
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code Date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public DateTime toModelType() throws IllegalValueException {
        if (!DateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(dateTime);
    }

}



