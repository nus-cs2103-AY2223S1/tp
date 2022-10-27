package seedu.rc4hdb.model.venues.booking;

import java.util.Optional;

import seedu.rc4hdb.commons.util.CollectionUtil;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * Stores the details to edit the booking with. Each non-empty field value will replace the
 * corresponding field value of the booking.
 */
public class BookingDescriptor {

    private VenueName venueName;
    private Resident resident;
    private HourPeriod hourPeriod;
    private Day dayOfWeek;

    public BookingDescriptor() {}

    /**
     * Copy constructor.
     */
    public BookingDescriptor(BookingDescriptor toCopy) {
        setVenueName(toCopy.venueName);
        setResident(toCopy.resident);
        setHourPeriod(toCopy.hourPeriod);
        setDayOfWeek(toCopy.dayOfWeek);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(resident, hourPeriod, dayOfWeek);
    }

    //=========== Start of Getters and Setters ===============================================================

    public void setVenueName(VenueName venueName) {
        this.venueName = venueName;
    }

    public Optional<VenueName> getVenueName() {
        return Optional.ofNullable(venueName);
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Optional<Resident> getResident() {
        return Optional.ofNullable(resident);
    }

    public void setHourPeriod(HourPeriod hourPeriod) {
        this.hourPeriod = hourPeriod;
    }

    public Optional<HourPeriod> getHourPeriod() {
        return Optional.ofNullable(hourPeriod);
    }

    public void setDayOfWeek(Day dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Optional<Day> getDayOfWeek() {
        return Optional.ofNullable(dayOfWeek);
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
                && getHourPeriod().equals(e.getHourPeriod())
                && getDayOfWeek().equals(e.getDayOfWeek());
    }
}
