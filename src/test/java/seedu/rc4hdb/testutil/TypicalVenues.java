package seedu.rc4hdb.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;

/**
 * A utility class containing a list of {@code Venue} objects to be used in tests.
 */
public class TypicalVenues {

    public static final String MEETING_ROOM_STRING = "Meeting Room";
    public static final VenueName MEETING_ROOM_VENUE_NAME = new VenueName(MEETING_ROOM_STRING);
    public static final Venue MEETING_ROOM = new Venue(MEETING_ROOM_VENUE_NAME);

    public static final String HALL_STRING = "Hall";
    public static final VenueName HALL_VENUE_NAME = new VenueName(HALL_STRING);
    public static final Venue HALL = new Venue(HALL_VENUE_NAME);

    public static final String DISCUSSION_ROOM_STRING = "Discussion Room";
    public static final VenueName DISCUSSION_ROOM_NAME = new VenueName(DISCUSSION_ROOM_STRING);
    public static final Venue DISCUSSION_ROOM = new Venue(DISCUSSION_ROOM_NAME);

    public static final String FUNCTION_ROOM_STRING = "Function Room";
    public static final VenueName FUNCTION_ROOM_NAME = new VenueName(FUNCTION_ROOM_STRING);
    public static final Venue FUNCTION_ROOM = new Venue(FUNCTION_ROOM_NAME);

    public static VenueBook getTypicalVenueBook() {
        VenueBook venueBook = new VenueBook();
        for (Venue venue : getTypicalVenues()) {
            venueBook.addVenue(venue);
        }
        return venueBook;
    }

    public static List<Venue> getTypicalVenues() {
        return new ArrayList<>(Arrays.asList(MEETING_ROOM, HALL, DISCUSSION_ROOM));
    }
}
