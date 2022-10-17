package seedu.uninurse.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.task.DateTime;

/**
 * Jackson-friendly version of {@link DateTime}
 */
public class JsonAdaptedDateTime {
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedDateTime} with the given {@code validDateTime}.
     */
    @JsonCreator
    public JsonAdaptedDateTime(String validDateTime) {
        this.dateTime = validDateTime;
    }

    /**
     * Converts a given {@code DateTime} into this class for Jackson use.
     */
    public JsonAdaptedDateTime(DateTime dateTime) {
        this.dateTime = dateTime.toString();
    }

    @JsonValue
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Converts this Jackson-friendly adapted DateTime object into the model's {@code DateTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted DateTime.
     */
    public DateTime toModelType() throws IllegalValueException {
        if (!DateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }

        return new DateTime(dateTime);
    }

}
