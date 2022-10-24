package gim.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import gim.commons.exceptions.IllegalValueException;
import gim.model.date.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedDate {

    private final String dateString;

    /**
     * Constructs a {@code JsonAdaptedDate} with the given {@code dateString}.
     */
    @JsonCreator
    public JsonAdaptedDate(String dateString) {
        this.dateString = dateString;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDate(Date source) {
        dateString = source.toString();
    }

    @JsonValue
    public String getDateString() {
        return dateString;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code Date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public Date toModelType() throws IllegalValueException {
        if (!Date.isValidDateByRegex(dateString)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(dateString);
    }

}
