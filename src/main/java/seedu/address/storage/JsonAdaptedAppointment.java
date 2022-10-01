package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Appointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    private final String reason;
    private final String dateTime;

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("reason") String reason, @JsonProperty("dateTime") String dateTime) {
        this.reason = reason;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        this.reason = source.reason;
        this.dateTime = source.dateTime.format(Appointment.STORAGE_FORMATTER);
    }


    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (!Appointment.isValidReason(reason)) {
            throw new IllegalValueException(Appointment.REASON_MESSAGE_CONSTRAINTS);
        }
        if (!Appointment.isValidDateTime(dateTime)) {
            throw new IllegalValueException(Appointment.DATE_MESSAGE_CONSTRAINTS);
        }
        return new Appointment(reason, dateTime);
    }
}
