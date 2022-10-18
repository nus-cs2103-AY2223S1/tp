package seedu.rc4hdb.model.venues;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.rc4hdb.model.resident.Resident;

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
    public AdHocBooking(Resident resident, String date, String startTime, String endTime) {
        super(resident, startTime, endTime);
        this.date = LocalDate.parse(date);
        setDayOfWeek(this.date.getDayOfWeek());
    }

    /**
     * Implements the hasExpired() method in the Booking class.
     * @return True if the current booking has expired.
     */
    @Override
    public boolean hasExpired() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = LocalDateTime.of(this.date, this.endTime);
        return currentDateTime.isAfter(expiryDateTime);
    }

    /**
     * Implements the clashesWith() method in the Booking class.
     * @param booking The booking to check for clashes with.
     * @return True if both bookings clash.
     */
    @Override
    public boolean clashesWith(Booking booking) {
        if (booking instanceof RecurrentBooking) {
            return booking.clashesWith(this);
        }
        if (booking instanceof AdHocBooking) {
            AdHocBooking otherBooking = (AdHocBooking) booking;
            boolean isOverlappingOrSubset = this.startTime.isAfter(otherBooking.startTime)
                    && this.startTime.isBefore(otherBooking.endTime)
                    || this.endTime.isAfter(otherBooking.startTime)
                    && this.endTime.isBefore(otherBooking.endTime);
            boolean isOverlappingOrSuperset = otherBooking.startTime.isAfter(this.startTime)
                    && otherBooking.startTime.isBefore(this.endTime)
                    || otherBooking.endTime.isAfter(this.startTime)
                    && otherBooking.endTime.isBefore(this.endTime);
            return otherBooking.date.equals(this.date)
                    && (isOverlappingOrSubset || isOverlappingOrSuperset);
        }
        return false;
    }
}
