package seedu.rc4hdb.testutil;

import seedu.rc4hdb.model.resident.Resident;

import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {

    public static final String DEFAULT_VENUE_NAME = "Hall";
    public static final String DEFAULT_TIME_PERIOD = "8-23";
    public static final String DEFAULT_DAY = "Fri";
    public static final Resident DEFAULT_RESIDENT = new ResidentBuilder().build();

    private VenueName venueName;
    private HourPeriod timePeriod;
    private Day day;
    private Resident resident;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        venueName = new VenueName(DEFAULT_VENUE_NAME);
        timePeriod = new HourPeriod(DEFAULT_TIME_PERIOD);
        day = new Day(DEFAULT_DAY);
        resident = DEFAULT_RESIDENT;
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     */
    public BookingBuilder(Booking booking) {
        venueName = booking.getVenueName();
        timePeriod = booking.getHourPeriod();
        day = booking.getDayOfWeek();
        resident = booking.getResident();
    }

    /**
     * Sets the {@code VenueName} of the {@code Booking} that we are building.
     */
    public BookingBuilder withVenueName(String venueName) {
        this.venueName = new VenueName(venueName);
        return this;
    }

    /**
     * Sets the {@code HourPeriod} of the {@code Booking} that we are building.
     */
    public BookingBuilder withTimePeriod(String timePeriod) {
        this.timePeriod = new HourPeriod(timePeriod);
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code Booking} that we are building.
     */
    public BookingBuilder withDay(String day) {
        this.day = new Day(day);
        return this;
    }

    public Booking build() {
        return new RecurrentBooking(venueName, resident, timePeriod, day);
    }

}
