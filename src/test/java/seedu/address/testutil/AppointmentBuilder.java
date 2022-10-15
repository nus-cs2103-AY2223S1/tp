package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Location;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_DATETIME = "21-Jan-2023 01:00 AM";
    public static final String DEFAULT_LOCATION = "301 Upper Thomson Rd, Singapore 574408";

    private DateTime dateTime;
    private Location location;


    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        dateTime = ParserUtil.parseDateTime(DEFAULT_DATETIME);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        dateTime = appointmentToCopy.getDateTime();
        location = appointmentToCopy.getLocation();
    }


    /**
     * Sets the {@code DateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDateTime(String dateTime) {
        this.dateTime = ParserUtil.parseDateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Returns a Person with the respective arguments as fields
     * By default, the set of appointments field is created but is empty
     */
    public Appointment build() {
        return new Appointment(dateTime,location);
    }

}
