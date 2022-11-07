package seedu.address.logic.parser;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;

/**
 * Stores the details to edit the appointment with. Each non-empty field value will replace the
 * corresponding field value of the appointment.
 *
 * @author Gerald Teo Jin Wei
 * @version 1.4
 * @since 2022-11-07
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

    /**
     * Set the newly edited DateTime
     * @param dateTime newly edited dateTime
     */
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Optional<Location> getLocation() {
        return Optional.ofNullable(location);
    }

    /**
     * Set the newly edited Location
     * @param location newly edited location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code apptToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    public Appointment createEditedAppointment(Appointment apptToEdit) {
        assert apptToEdit != null;

        DateTime updatedDateTime = this.getDateTime().orElse(apptToEdit.getDateTime());
        Location updatedLocation = this.getLocation().orElse(apptToEdit.getLocation());

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
