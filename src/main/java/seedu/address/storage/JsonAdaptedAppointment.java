package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {
    private final String appointmentName;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given {@code appointmentName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointmentName) {
        this.appointmentName = appointmentName;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentName = source.toString();
    }

    @JsonValue
    public String getAppointmentName() {
        return appointmentName;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     */
    public Appointment toModelType() {
        String dateAndTime = appointmentName.trim();
        LocalDateTime localDateTime = DateTimeParser.parseLocalDateTimeFromString(dateAndTime.trim());
        DateTime dateTime = new DateTime(localDateTime);
        return new Appointment(dateTime);
    }

}
