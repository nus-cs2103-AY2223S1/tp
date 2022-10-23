package seedu.rc4hdb.model.venues.booking;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Represents a booking for a venue. Bookings cannot span multiple days.
 */
public abstract class Booking {

    protected Venue venue;
    protected Resident resident;
    protected LocalTime startHour;
    protected LocalTime endHour;
    protected DayOfWeek dayOfWeek;

    /**
     * Constructor for a Booking instance.
     * @param resident The resident trying to book the venue.
     * @param startTime The start time of the booking.
     * @param endTime The end time of the booking.
     * @param venue The venue of the booking.
     */
    public Booking(Resident resident, LocalTime startTime, LocalTime endTime, Venue venue) {
        this.resident = resident;
        this.startHour = startTime;
        this.endHour = endTime;
        this.venue = venue;
    }

    public Venue getVenue() {
        return this.venue;
    }

    public Resident getResident() {
        return this.resident;
    }

    public LocalTime getStartHour() {
        return this.startHour;
    }

    public LocalTime getEndHour() {
        return this.endHour;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    protected void setDayOfWeek(DayOfWeek dayOfWeek) {
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
}
