/*
package seedu.workbook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.workbook.commons.exceptions.IllegalValueException;
import seedu.workbook.model.internship.DateTime;

public class JsonAdaptedDateTime {

    private final String date;
    */
/**
     * Constructs a {@code JsonAdaptedDateTime} with the given {@code daye}.
     *//*

    @JsonCreator
    public JsonAdaptedDateTime(String date) {
        this.date = date;
    }

    */
/**
     * Converts a given {@code DateTime} into this class for Jackson use.
     *//*

    public JsonAdaptedDateTime(DateTime source) {
        date = source.value;
    }

    @JsonValue
    public String getDateTime() {
        return date;
    }

    */
/**
     * Converts this Jackson-friendly adapted date object into the model's {@code DateTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     *//*

    public DateTime toModelType() throws IllegalValueException {
        if (!DateTime.isValidDate(date)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(date);
    }
}
*/
