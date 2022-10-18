package seedu.rc4hdb.model.venues;

import java.time.DayOfWeek;

import seedu.rc4hdb.model.resident.Resident;

public class RecurrentBooking extends Booking {

    /**
     * Constructor for a RecurrentBooking instance.
     * @param resident The resident trying to book the venue.
     * @param dayOfWeek The day of the week of the booking.
     * @param startTime The start time of the booking.
     * @param endTime The end time of the booking.
     */
    public RecurrentBooking(Resident resident, String dayOfWeek, String startTime, String endTime) {
        super(resident, startTime, endTime);
        setDayOfWeek(DayOfWeek.valueOf(dayOfWeek));
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
        return booking.startTime.isAfter(this.startTime)
                && booking.startTime.isBefore(this.endTime)
                || booking.endTime.isAfter(this.startTime)
                && booking.endTime.isBefore(this.endTime);
    }
}
