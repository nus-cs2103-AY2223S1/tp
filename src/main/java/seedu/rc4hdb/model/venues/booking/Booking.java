package seedu.rc4hdb.model.venues.booking;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * Represents a booking for a venue. Bookings cannot span multiple days.
 */
public abstract class Booking {

    protected VenueName venueName;
    protected Resident resident;
    protected HourPeriod hourPeriod;
    protected Day dayOfWeek;

    /**
     * Constructor for a Booking instance.
     * @param venueName The name of the venue.
     * @param resident The resident trying to book the venue.
     * @param hourPeriod The period of time that the venue is to be booked.
     * @param dayOfWeek The day of the week the venue is to be booked.
     */
    public Booking(VenueName venueName, Resident resident, HourPeriod hourPeriod, Day dayOfWeek) {
        requireAllNonNull(venueName, resident, hourPeriod, dayOfWeek);
        this.venueName = venueName;
        this.resident = resident;
        this.hourPeriod = hourPeriod;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Checks if the booking has expired.
     * @return True if the booking has expired.
     */
    public abstract boolean hasExpired();

    /**
     * Checks if the booking period overlaps with another booking period.
     * @param booking The booking to check overlap with.
     * @return True if both bookings overlap.
     */
    public abstract boolean clashesWith(Booking booking);

    /**
     * Checks if the booking lies on the same day and time period.
     * @param hourPeriod the booking period of the booking to be checked.
     * @param bookingDay the day of the booking to be checked
     * @return True if the booking lies on the same day and time period.
     */
    public boolean isSameBooking(HourPeriod hourPeriod, Day bookingDay) {
        return this.hourPeriod.equals(hourPeriod)
                && this.dayOfWeek.equals(bookingDay);
    }

    //====================== Start of getters ===============================

    public VenueName getVenueName() {
        return venueName;
    }

    public Resident getResident() {
        return resident;
    }

    public HourPeriod getHourPeriod() {
        return hourPeriod;
    }

    public Day getDayOfWeek() {
        return dayOfWeek;
    }

    //====================== End of getters =================================

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(resident.getName())
                .append("; Venue: ")
                .append(venueName)
                .append("; Start Time: ")
                .append(hourPeriod.getStart())
                .append("; End Time: ")
                .append(hourPeriod.getEnd())
                .append("; Day: ")
                .append(dayOfWeek);

        return builder.toString();
    }

}
