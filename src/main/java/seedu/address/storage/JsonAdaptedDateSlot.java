package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DateSlot;

/**
 * Jackson-friendly version of {@link DateSlot}.
 */
class JsonAdaptedDateSlot {

    private final String dateSlot;

    /**
     * Constructs a {@code JsonAdaptedDateTime} with the given {@code DateTime}.
     */
    @JsonCreator
    public JsonAdaptedDateSlot(String dateSlot) {
        this.dateSlot = dateSlot;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDateSlot(DateSlot source) {
        dateSlot = source.getString();
    }

    @JsonValue
    public String getDateSlot() {
        return dateSlot;
    }

    /**
     * Converts this Jackson-friendly adapted date slot object into the model's {@code DateSlot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date slot.
     */
    public DateSlot toModelType() throws IllegalValueException {
        if (!DateSlot.isValidDateSlot(dateSlot)) {
            throw new IllegalValueException(DateSlot.MESSAGE_CONSTRAINTS);
        }
        return new DateSlot(dateSlot);
    }

}
