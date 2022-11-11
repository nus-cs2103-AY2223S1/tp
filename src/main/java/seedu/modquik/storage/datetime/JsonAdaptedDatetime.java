package seedu.modquik.storage.datetime;

import static seedu.modquik.model.datetime.Datetime.fromFormattedString;
import static seedu.modquik.model.datetime.DatetimeCommonUtils.DATETIME_MESSAGE_CONSTRAINTS;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.modquik.commons.exceptions.IllegalValueException;
import seedu.modquik.model.datetime.Datetime;

/**
 * Jackson-friendly version of {@link Datetime}.
 */
public class JsonAdaptedDatetime {

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
            throw new IllegalValueException(DATETIME_MESSAGE_CONSTRAINTS);
        }
        return fromFormattedString(datetime);
    }
}
