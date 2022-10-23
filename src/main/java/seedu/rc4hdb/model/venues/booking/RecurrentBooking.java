package seedu.rc4hdb.model.venues.booking;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.Hour;

/**
 * Represents a recurrent booking for a venue.
 */
public class RecurrentBooking extends Booking {

    /**
     * Constructor for a RecurrentBooking instance.
     * @param resident The resident trying to book the venue.
     * @param dayOfWeek The day of the week of the booking.
     * @param startHour The start time of the booking.
     * @param endHour The end time of the booking.
     * @param venue The venue to be booked.
     */
    public RecurrentBooking(Resident resident, Hour startHour, Hour endHour, Day dayOfWeek, Venue venue) {
        super(resident, startHour, endHour, dayOfWeek, venue);
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
     * @param booking
     * @return True if both bookings clash.
     */
    @Override
    public boolean clashesWith(Booking booking) {
        if (dayOfWeek.equals(booking.dayOfWeek)) {
            return false;
        }
        return true;
    }

}
