package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedDate {

    private final String date;

    /**
     * Constructs a {@code JsonAdaptedDate} with the given {@code Date}.
     */
    @JsonCreator
    public JsonAdaptedDate(String date) {
        this.date = date;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDate(Date source) {
        date = source.getString();
    }

    @JsonValue
    public String getDate() {
        return date;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's
     * {@code Date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted date.
     */
    public Date toModelType() throws IllegalValueException {
        if (!Date.isValidDateFormat(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(date);
    }

}
