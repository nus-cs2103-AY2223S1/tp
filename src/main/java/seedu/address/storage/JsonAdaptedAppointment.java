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
    private final String period;
    private final boolean isMarked;

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("reason") String reason,
                                  @JsonProperty("dateTime") String dateTime,
                                  @JsonProperty("period") String period,
                                  @JsonProperty("isMarked") boolean isMarked) {
        this.reason = reason;
        this.dateTime = dateTime;
        this.period = period;
        this.isMarked = isMarked;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        this.reason = source.getReason();
        this.dateTime = source.getDateTime().format(Appointment.STORAGE_FORMATTER);
        this.period = source.getFormattedPeriod();
        this.isMarked = source.isMarked();
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

        if (!Appointment.isValidTimePeriod(period)) {
            throw new IllegalValueException(Appointment.TIME_PERIOD_MESSAGE_CONSTRAINTS);
        }

        return new Appointment(reason, dateTime, period, isMarked);
    }
}
