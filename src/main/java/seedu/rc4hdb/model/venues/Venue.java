package seedu.rc4hdb.model.venues;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a venue in RC4 that can be booked by residents.
 */
public class Venue {
    private List<Booking> bookings = List.of();
    private String venueIndex;
    private String venueName;

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
     * Removes expired bookings from the list of bookings.
     */
    public void clearExpiredBookings() {
        this.bookings = this.bookings.stream()
                .filter(b -> !b.hasExpired())
                .collect(Collectors.toList());
    }

    // Todo: Populate venue table with the venues specified in the .txt file and hook up class with storage
}
