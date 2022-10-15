package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Location;

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
    public Appointment toModelType() {
        String dateAndTime = appointmentDateTime.trim();
        LocalDateTime localDateTime = DateTimeParser.parseLocalDateTimeFromString(dateAndTime.trim());
        DateTime dateTime = new DateTime(localDateTime);
        Location location = new Location(appointmentLocation);
        return new Appointment(dateTime, location);
    }

}
