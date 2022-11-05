package seedu.address.logic.parser;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;

/**
 * Stores the details to edit the appointment with. Each non-empty field value will replace the
 * corresponding field value of the appointment.
 */
public class EditAppointmentDescriptor {
    private DateTime dateTime;
    private Location location;


    public EditAppointmentDescriptor() {};
    /**
     * Copy constructor.
     */
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
        this.location = location;
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    public static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                      EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        DateTime updatedDateTime = editAppointmentDescriptor.getDateTime().orElse(appointmentToEdit.getDateTime());
        Location updatedLocation = editAppointmentDescriptor.getLocation().orElse(appointmentToEdit.getLocation());

        return new Appointment(updatedDateTime, updatedLocation);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentDescriptor)) {
            return false;
        }

        // state check
        EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

        return getDateTime().equals(e.getDateTime())
                && getLocation().equals(e.getLocation());
    }
}
