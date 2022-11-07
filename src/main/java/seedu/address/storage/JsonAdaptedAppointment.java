package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {
    private final String appointmentDateTime;
    private final String appointmentLocation;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with
     * the given {@code appointmentDateTime} and {@code appointmentLocation}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("dateTime") String dateTime,
                                  @JsonProperty("location") String location) {
        this.appointmentDateTime = dateTime;
        this.appointmentLocation = location;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentDateTime = source.getDateTime().toString();
        appointmentLocation = source.getLocation().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (appointmentLocation == null) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        if (!Location.isValidLocation(appointmentLocation)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        Location location = new Location(appointmentLocation);

        if (appointmentDateTime == null) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        if (!DateTimeParser.isValidDateTime(appointmentDateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        LocalDateTime localDateTime = DateTimeParser.parseLocalDateTimeFromString(appointmentDateTime);
        DateTime dateTime = new DateTime(localDateTime);
        return new Appointment(dateTime, location);
    }

}
