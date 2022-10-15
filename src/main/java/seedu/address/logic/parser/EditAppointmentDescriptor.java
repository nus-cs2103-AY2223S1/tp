package seedu.address.logic.parser;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Location;

/**
 * Stores the details to edit the appointment with. Each non-empty field value will replace the
 * corresponding field value of the appointment.
 */
public class EditAppointmentDescriptor {
    private DateTime dateTime;
    private Location location;


    public EditAppointmentDescriptor() {};

    public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
        setDateTime(toCopy.dateTime);
        setLocation(toCopy.location);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(dateTime, location);
    }

    public Optional<DateTime> getDateTime() {
        return Optional.ofNullable(dateTime);
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    public void setLocation(Location location) {
        this.dateTime = dateTime;
    }

    public static Appointment createEditedAppointment(Appointment appointmentToEdit, EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        DateTime updatedDateTime = editAppointmentDescriptor.getDateTime().orElse(appointmentToEdit.getDateTime());
        Location updatedLocation = editAppointmentDescriptor.getLocation().orElse(appointmentToEdit.getLocation());

        return new Appointment(updatedDateTime, updatedLocation);
    }
}
