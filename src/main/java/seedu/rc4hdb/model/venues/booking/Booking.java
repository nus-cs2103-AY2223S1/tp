package seedu.rc4hdb.model.venues.booking;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * Represents a booking for a venue. Bookings cannot span multiple days.
 */
public abstract class Booking {

    protected Venue venue;
    protected Resident resident;
    protected HourPeriod hourPeriod;
    protected Day dayOfWeek;

    /**
     * Constructor for a Booking instance.
     * @param resident The resident trying to book the venue.
     * @param hourPeriod The period of time that the venue is to be booked.
     * @param venue The venue of the booking.
     */
    public Booking(Resident resident, HourPeriod hourPeriod, Day dayOfWeek, Venue venue) {
        requireAllNonNull(resident, hourPeriod, dayOfWeek, venue);
        this.resident = resident;
        this.hourPeriod = hourPeriod;
        this.dayOfWeek = dayOfWeek;
        this.venue = venue;
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

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(resident.getName())
                .append("; Venue: ")
                .append(venue)
                .append("; Start Time: ")
                .append(hourPeriod.getStart())
                .append("; End Time: ")
                .append(hourPeriod.getEnd())
                .append("; Day: ")
                .append(dayOfWeek);

        return builder.toString();
    }

}
