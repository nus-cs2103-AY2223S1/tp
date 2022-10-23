package seedu.rc4hdb.model.venues.booking;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Represents a recurrent booking for a venue.
 */
public class RecurrentBooking extends Booking {

    /**
     * Constructor for a RecurrentBooking instance.
     * @param resident The resident trying to book the venue.
     * @param dayOfWeek The day of the week of the booking.
     * @param startTime The start time of the booking.
     * @param endTime The end time of the booking.
     */
    public RecurrentBooking(Resident resident, DayOfWeek dayOfWeek, LocalTime startTime,
                            LocalTime endTime, Venue venue) {
        super(resident, startTime, endTime, venue);
        setDayOfWeek(dayOfWeek);
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
        if (!booking.dayOfWeek.equals(this.dayOfWeek)) {
            return false;
        }
        if (!booking.venue.equals(this.venue)) {
            return false;
        }
        boolean isOverlappingOrSubset = this.startHour.isAfter(booking.startHour)
                && this.startHour.isBefore(booking.endHour)
                || this.endHour.isAfter(booking.startHour)
                && this.endHour.isBefore(booking.endHour);
        boolean isOverlappingOrSuperset = booking.startHour.isAfter(this.startHour)
                && booking.startHour.isBefore(this.endHour)
                || booking.endHour.isAfter(this.startHour)
                && booking.endHour.isBefore(this.endHour);
        return isOverlappingOrSubset || isOverlappingOrSuperset;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getResident().getName())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Start Time: ")
                .append(getStartHour())
                .append("; End Time: ")
                .append(getEndHour())
                .append("; Day: ")
                .append(getDayOfWeek());

        return builder.toString();
    }
}
