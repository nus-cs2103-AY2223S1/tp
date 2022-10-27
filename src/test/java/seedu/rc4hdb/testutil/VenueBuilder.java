package seedu.rc4hdb.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6_PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_TUESDAY_6_TO_7PM;

public class VenueBuilder {

    public static final String DEFAULT_VENUE_NAME = "Multi Purpose Sports Hall";
    public static final List<Booking> DEFAULT_BOOKINGS = new ArrayList<>(
            List.of(MR_ALICE_MONDAY_5_TO_6_PM, MR_ALICE_TUESDAY_6_TO_7PM));

    private VenueName venueName;
    private List<Booking> bookings;

    public VenueBuilder() {
        venueName = new VenueName(DEFAULT_VENUE_NAME);
        bookings = DEFAULT_BOOKINGS;
    }

    public VenueBuilder(Venue venueToCopy) {
        venueName = venueToCopy.getVenueName();
        bookings = new ArrayList<>(venueToCopy.getObservableBookings());
    }

    public VenueBuilder withVenueName(VenueName otherVenueName) {
        venueName = otherVenueName;
        return this;
    }

    public VenueBuilder withBookings(Booking... otherBookings) {
        bookings = new ArrayList<>(List.of(otherBookings));
        return this;
    }

    public Venue build() {
        return new Venue(bookings, venueName);
    }
}
