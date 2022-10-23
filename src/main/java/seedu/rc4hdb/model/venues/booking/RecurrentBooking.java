package seedu.rc4hdb.model.venues.booking;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * Represents a recurrent booking for a venue.
 */
public class RecurrentBooking extends Booking {

    /**
     * Constructor for a RecurrentBooking instance.
     * @param resident The resident trying to book the venue.
     * @param dayOfWeek The day of the week of the booking.
     * @param hourPeriod The period of time that the venue is booked.
     * @param venue The venue to be booked.
     */
    public RecurrentBooking(Resident resident, HourPeriod hourPeriod, Day dayOfWeek, Venue venue) {
        super(resident, hourPeriod, dayOfWeek, venue);
    }

    /**
     * Implements the hasExpired() method in the Booking class. A RecurrentBooking does not expire.
     * @return False
     */
    @Override
    public boolean hasExpired() {
        return false;
    }

    /**
     * Implements the clashesWith() method in the Booking class. Compares bookings based on day of the week.
     * @param other the other booking to compare with.
     * @return True if both bookings clash.
     */
    @Override
    public boolean clashesWith(Booking other) {
        return dayOfWeek.equals(other.dayOfWeek)
                && hourPeriod.clashesWith(other.hourPeriod);
    }

}
