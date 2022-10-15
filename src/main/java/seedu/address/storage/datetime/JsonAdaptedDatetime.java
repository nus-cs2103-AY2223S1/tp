package seedu.address.storage.datetime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.datetime.Datetime;

/**
 * Jackson-friendly version of {@link Datetime}.
 */
class JsonAdaptedDatetime {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    public final String datetime;

    /**
     * Constructs a {@code JsonAdaptedDatetime} with the given datetime details.
     */
    @JsonCreator
    public JsonAdaptedDatetime(@JsonProperty("datetime") String datetime) {
        this.datetime = datetime;
    }

    /**
     * Converts a given {@code JsonAdaptedDatetime} into this class for Jackson use.
     */
    public JsonAdaptedDatetime(Datetime source) {
        datetime = source.toFormatted();
    }

    /**
     * Converts this Jackson-friendly adapted datetime object into the model's {@code JsonAdaptedDatetime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted datetime.
     */
    public Datetime toModelType() throws IllegalValueException {
        if (datetime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Datetime.class.getSimpleName()));
        }
        if (!Datetime.isValidDatetime(datetime)) {
            throw new IllegalValueException(Datetime.MESSAGE_CONSTRAINTS);
        }
        return new Datetime(datetime);
    }
}
