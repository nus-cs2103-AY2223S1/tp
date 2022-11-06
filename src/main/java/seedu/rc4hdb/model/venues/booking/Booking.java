package seedu.rc4hdb.model.venues.booking;

import static java.util.Objects.requireNonNull;
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
     * @param other The booking to check overlap with.
     * @return True if both bookings overlap.
     */
    public boolean clashesWith(Booking other) {
        requireNonNull(other);
        return dayOfWeek.equals(other.dayOfWeek)
                && hourPeriod.clashesWith(other.hourPeriod);
    }

    /**
     * Checks if the booking is on the same day as {@code day}.
     * @param day The day to check the booking against.
     * @return True if the booking is on the same day.
     */
    public boolean isSameDay(Day day) {
        requireNonNull(day);
        return this.dayOfWeek.equals(day);
    }

    /**
     * Checks if the booking is on the same period as {@code hourPeriod}.
     * @param hourPeriod The hour period to check the booking against.
     * @return True if the booking is on the same period.
     */
    public boolean isSamePeriod(HourPeriod hourPeriod) {
        requireNonNull(hourPeriod);
        return this.hourPeriod.equals(hourPeriod);
    }
    /**
     * Checks if the booking descriptor matches the booking.
     * @param bookingDescriptor The descriptor to check against.
     * @return True if the booking lies on the same day and time period.
     */
    public boolean isSameBooking(BookingDescriptor bookingDescriptor) {
        requireNonNull(bookingDescriptor);
        return bookingDescriptor.getHourPeriod().map(this::isSamePeriod).orElse(false)
                && bookingDescriptor.getDayOfWeek().map(this::isSameDay).orElse(false);
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

    //====================== Compare Days =================================

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Booking
                && (this.venueName.equals(((Booking) other).venueName)
                && (this.resident.equals(((Booking) other).resident))
                && (this.hourPeriod.equals(((Booking) other).hourPeriod))
                && (this.dayOfWeek.equals(((Booking) other).dayOfWeek))));
    }

}
