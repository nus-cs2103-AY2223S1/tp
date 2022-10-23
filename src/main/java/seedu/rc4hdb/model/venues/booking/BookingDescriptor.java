package seedu.rc4hdb.model.venues.booking;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import seedu.rc4hdb.commons.util.CollectionUtil;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;


/**
 * Stores the details to edit the booking with. Each non-empty field value will replace the
 * corresponding field value of the booking.
 */
public class BookingDescriptor {
    private Resident resident;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;
    private LocalDate date;
    private Venue venue;

    public BookingDescriptor() {}

    /**
     * Copy constructor.
     */
    public BookingDescriptor(BookingDescriptor toCopy) {
        setResident(toCopy.resident);
        setStartTime(toCopy.startTime);
        setEndTime(toCopy.endTime);
        setDayOfWeek(toCopy.dayOfWeek);
        setDate(toCopy.date);
        setVenue(toCopy.venue);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(resident, startTime, endTime, dayOfWeek, date);
    }

    //=========== Start of Getters and Setters ===============================================================

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Optional<Resident> getResident() {
        return Optional.ofNullable(resident);
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Optional<LocalTime> getstartTime() {
        return Optional.ofNullable(startTime);
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Optional<LocalTime> getEndTime() {
        return Optional.ofNullable(endTime);
    }

    public void setDayOfWeek(DayOfWeek dayofWeek) {
        this.dayOfWeek = dayofWeek;
    }

    public Optional<DayOfWeek> getDayOfWeek() {
        return Optional.ofNullable(dayOfWeek);
    }

    public void setDate(LocalDate date) {
        this.date = (date != null) ? this.date = date : null;
    }

    public Optional<LocalDate> getDate() {
        return (date != null) ? Optional.of(date) : Optional.empty();
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
                && getstartTime().equals(e.getstartTime())
                && getEndTime().equals(e.getEndTime())
                && getDayOfWeek().equals(e.getDayOfWeek())
                && getDate().equals(e.getDate())
                && getVenue().equals(e.getVenue());
    }
}
