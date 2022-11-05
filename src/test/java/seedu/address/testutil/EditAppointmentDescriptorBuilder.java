package seedu.address.testutil;

import seedu.address.logic.parser.EditAppointmentDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Location;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {
    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code person}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setDateTime(appointment.getDateTime());
        descriptor.setLocation(appointment.getLocation());
    }

    /**
     * Sets the {@code DateTime} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(ParserUtil.parseDateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
