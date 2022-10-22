package seedu.rc4hdb.model.venues;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Represents a venue in RC4 that can be booked by residents.
 */
public class Venue {

    public static final String MESSAGE_CONSTRAINTS =
            "Venue should only consist of: 'meeting' ";

    public static final String VALID_ROOM = "meeting";

    //IMPORTANT: Venue is a singleton with just one Venue, Meeting Room as of now
    public static final Venue MEETING_ROOM = new Venue("1", "Meeting Room");

    private List<Booking> bookings = new ArrayList<>();
    private String venueName;
    private String venueIndex;

    /**
     * Constructor for a Venue instance.
     * @param venueIndex The index of the venue as specified by the storage file.
     * @param venueName The name of the venue as specified by the storage file.
     */
    public Venue(String venueIndex, String venueName) {
        this.venueIndex = venueIndex;
        this.venueName = venueName;
    }

    /**
     * Returns true if a given venue is a valid venue.
     */
    public static boolean isValidVenue(String test) {
        return test.toLowerCase(Locale.ROOT).equals(VALID_ROOM);
    }

    /**
     * Removes expired bookings from the list of bookings.
     */
    public void clearExpiredBookings() {
        this.bookings = this.bookings.stream()
                .filter(b -> !b.hasExpired())
                .collect(Collectors.toList());
    }

    /**
     * Adds booking to the list of bookings.
     *
     * @param booking The booking to be added.
     */

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public boolean hasClashes(Booking booking) {
        requireNonNull(booking);
        for (Booking b : this.bookings) {
            if (b.clashesWith(booking)) {
                return true;
            }
        }
        return false;
    }

    public List<Booking> getFilteredBookings() {
        clearExpiredBookings();
        return this.bookings;
    }

    @Override
    public String toString() {
        return this.venueName;
    }

    // Todo: Populate venue table with the venues specified in the .txt file and hook up class with storage
}
