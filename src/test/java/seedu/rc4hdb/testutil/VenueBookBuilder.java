package seedu.rc4hdb.testutil;

import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.venues.Venue;

/**
 * A utility class to help with building ResidentBook objects.
 * Example usage: <br>
 *     {@code ResidentBook ab = new ResidentBookBuilder().withResident("John", "Doe").build();}
 */
public class VenueBookBuilder {

    private VenueBook venueBook;

    public VenueBookBuilder() {
        venueBook = new VenueBook();
    }

    public VenueBookBuilder(VenueBook venueBook) {
        this.venueBook = venueBook;
    }

    /**
     * Adds a new {@code Resident} to the {@code AddressBook} that we are building.
     */
    public VenueBookBuilder withVenue(Venue venue) {
        venueBook.addVenue(venue);
        return this;
    }

    public VenueBook build() {
        return venueBook;
    }
}

