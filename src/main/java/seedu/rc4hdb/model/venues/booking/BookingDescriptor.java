package seedu.rc4hdb.model.venues.booking;

import java.util.Optional;

import seedu.rc4hdb.commons.util.CollectionUtil;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.Hour;

/**
 * Stores the details to edit the booking with. Each non-empty field value will replace the
 * corresponding field value of the booking.
 */
public class BookingDescriptor {

    private Resident resident;
    private Hour startHour;
    private Hour endHour;
    private Day dayOfWeek;
    private Venue venue;

    public BookingDescriptor() {}

    /**
     * Copy constructor.
     */
    public BookingDescriptor(BookingDescriptor toCopy) {
        setResident(toCopy.resident);
        setStartHour(toCopy.startHour);
        setEndHour(toCopy.endHour);
        setDayOfWeek(toCopy.dayOfWeek);
        setVenue(toCopy.venue);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(resident, startHour, endHour, dayOfWeek);
    }

    //=========== Start of Getters and Setters ===============================================================

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Optional<Resident> getResident() {
        return Optional.ofNullable(resident);
    }

    public void setStartHour(Hour startHour) {
        this.startHour = startHour;
    }

    public Optional<Hour> getStartHour() {
        return Optional.ofNullable(startHour);
    }

    public void setEndHour(Hour endHour) {
        this.endHour = endHour;
    }

    public Optional<Hour> getEndHour() {
        return Optional.ofNullable(endHour);
    }

    public void setDayOfWeek(Day dayofWeek) {
        this.dayOfWeek = dayofWeek;
    }

    public Optional<Day> getDayOfWeek() {
        return Optional.ofNullable(dayOfWeek);
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Optional<Venue> getVenue() {
        return Optional.ofNullable(this.venue);
    }

    //=========== End of Getters and Setters =================================================================

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingDescriptor)) {
            return false;
        }

        // state check
        BookingDescriptor e = (BookingDescriptor) other;

        return getResident().equals(e.getResident())
                && getStartHour().equals(e.getStartHour())
                && getEndHour().equals(e.getEndHour())
                && getDayOfWeek().equals(e.getDayOfWeek())
                && getVenue().equals(e.getVenue());
    }
}
