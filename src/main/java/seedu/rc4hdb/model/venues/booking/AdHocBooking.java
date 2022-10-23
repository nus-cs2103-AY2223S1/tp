package seedu.rc4hdb.model.venues.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Represents a one-off booking for a venue.
 */
public class AdHocBooking extends Booking {
    private LocalDate date;

    /**
     * Constructor for an AdHocBooking instance.
     * @param resident The resident trying to book the venue.
     * @param date The date for the booking.
     * @param startTime The start time of the booking.
     * @param endTime The end time of the booking.
     */
    public AdHocBooking(Resident resident, LocalDate date, LocalTime startTime, LocalTime endTime, Venue venue) {
        super(resident, startTime, endTime, venue);
        this.date = date;
        setDayOfWeek(this.date.getDayOfWeek());
    }

    /**
     * Implements the hasExpired() method in the Booking class.
     * @return True if the current booking has expired.
     */
    @Override
    public boolean hasExpired() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = LocalDateTime.of(this.date, this.endHour);
        return currentDateTime.isAfter(expiryDateTime);
    }

    /**
     * Implements the clashesWith() method in the Booking class.
     * @param booking The booking to check for clashes with.
     * @return True if both bookings clash.
     */
    @Override
    public boolean clashesWith(Booking booking) {
        if (!booking.venue.equals(this.venue)) {
            return false;
        }
        if (booking instanceof RecurrentBooking) {
            return booking.clashesWith(this);
        }
        if (booking instanceof AdHocBooking) {
            AdHocBooking otherBooking = (AdHocBooking) booking;
            boolean isOverlappingOrSubset = this.startHour.isAfter(otherBooking.startHour)
                    && this.startHour.isBefore(otherBooking.endHour)
                    || this.endHour.isAfter(otherBooking.startHour)
                    && this.endHour.isBefore(otherBooking.endHour);
            boolean isOverlappingOrSuperset = otherBooking.startHour.isAfter(this.startHour)
                    && otherBooking.startHour.isBefore(this.endHour)
                    || otherBooking.endHour.isAfter(this.startHour)
                    && otherBooking.endHour.isBefore(this.endHour);
            return otherBooking.date.equals(this.date)
                    && (isOverlappingOrSubset || isOverlappingOrSuperset);
        }
        return false;
    }
}
