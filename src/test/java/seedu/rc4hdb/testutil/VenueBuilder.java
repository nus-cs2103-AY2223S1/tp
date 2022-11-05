package seedu.rc4hdb.testutil;

import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalVenues.HALL_STRING;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;

/**
 * A utility class to help with building Venue objects/
 */
public class VenueBuilder {

    public static final String DEFAULT_VENUE_NAME = HALL_STRING;
    public static final List<Booking> DEFAULT_BOOKINGS = new ArrayList<>(
            List.of(MR_ALICE_MONDAY_5_TO_6PM, MR_ALICE_TUESDAY_6_TO_7PM));

    private VenueName venueName;
    private List<Booking> bookings;

    /**
     * Creates a {@code VenueBuilder} with the default details.
     */
    public VenueBuilder() {
        venueName = new VenueName(DEFAULT_VENUE_NAME);
        bookings = DEFAULT_BOOKINGS;
    }

    /**
     * Initializes the ResidentBuilder with the data of {@code residentToCopy}.
     * @param venueToCopy The venue whose fields are to be copied
     */
    public VenueBuilder(Venue venueToCopy) {
        venueName = venueToCopy.getVenueName();
        bookings = new ArrayList<>(venueToCopy.getBookings());
    }

    /**
     * Sets the {@code VenueName} of the {@code Venue} that we are building.
     * @param otherVenueName The other venue name to modify the to-be-built venue with
     * @return This {@code VenueBuilder} instance with the venue name updated
     */
    public VenueBuilder withVenueName(VenueName otherVenueName) {
        venueName = otherVenueName;
        return this;
    }

    /**
     * Sets the {@code Bookings} of the {@code Venue} that we are building.
     * @param otherBookings The other bookings to modify the to-be-built venue with
     * @return This {@code VenueBuilder} instance with its bookings name updated
     */
    public VenueBuilder withBookings(Booking... otherBookings) {
        bookings = new ArrayList<>(List.of(otherBookings));
        return this;
    }

    /**
     * Builds a {@code Venue} out of the specified fields in the {@code VenueBuilder}.
     * @return A venue with the intended bookings and venue name
     */
    public Venue build() {
        return new Venue(bookings, venueName);
    }
}
