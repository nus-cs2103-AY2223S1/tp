package hobbylist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import hobbylist.commons.exceptions.IllegalValueException;
import hobbylist.model.date.Date;

/**
 * Jackson-friendly version of Date.
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
    @JsonCreator
    public JsonAdaptedDate(Date date) {
        this.dateString = date.getOrginString();
    }
    @JsonValue
    public String getDateName() {
        return this.dateString;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Date toModelType() throws IllegalValueException {
        if (!Date.isValidDateString(dateString)) {
            throw new IllegalValueException(Date.MESSAGE_EXCEPTION);
        }
        return new Date(dateString);
    }
}
