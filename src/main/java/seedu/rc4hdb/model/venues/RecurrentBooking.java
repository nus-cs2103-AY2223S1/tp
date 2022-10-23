package seedu.rc4hdb.model.venues;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.rc4hdb.model.resident.Resident;

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
        boolean isOverlappingOrSubset = this.startTime.isAfter(booking.startTime)
                && this.startTime.isBefore(booking.endTime)
                || this.endTime.isAfter(booking.startTime)
                && this.endTime.isBefore(booking.endTime);
        boolean isOverlappingOrSuperset = booking.startTime.isAfter(this.startTime)
                && booking.startTime.isBefore(this.endTime)
                || booking.endTime.isAfter(this.startTime)
                && booking.endTime.isBefore(this.endTime);
        return isOverlappingOrSubset || isOverlappingOrSuperset;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getResident().getName())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Start Time: ")
                .append(getStartTime())
                .append("; End Time: ")
                .append(getEndTime())
                .append("; Day: ")
                .append(getDayOfWeek());

        return builder.toString();
    }
}
