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

    public Venue(String venueIndex, String venueName) {
        this.venueIndex = venueIndex;
        this.venueName = venueName;
    }

    public void clearExpiredBookings() {
        this.bookings = this.bookings.stream()
                .filter(b -> !b.hasExpired())
                .collect(Collectors.toList());
    }

    // Todo: Populate venue table with the venues specified in the .txt file and hook up class with storage
}
