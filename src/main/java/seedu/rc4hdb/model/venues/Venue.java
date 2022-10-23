package seedu.rc4hdb.model.venues;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.fields.BookingField;
import seedu.rc4hdb.model.venues.exceptions.BookingClashesException;

/**
 * Represents a venue in RC4 that can be booked by residents.
 */
public class Venue implements BookingField {

    public static final String IDENTIFIER = "Venue";

    public static final String MESSAGE_CONSTRAINTS =
            "Venue should only consist of: 'meeting' ";

    public static final String VALID_ROOM = "meeting";

    //IMPORTANT: Venue is a singleton with just one Venue, Meeting Room as of now
    public static final Venue MEETING_ROOM = new Venue(new VenueName("Meeting Room"));

    private ObservableList<Booking> bookings = FXCollections.observableArrayList();
    private final VenueName venueName;

    /**
     * Constructor for a Venue instance.
     * @param venueName The name of the venue as specified by the venue book.
     */
    public Venue(VenueName venueName) {
        this.venueName = venueName;
    }

    /**
     * Removes expired bookings from the list of bookings.
     */
    public void clearExpiredBookings() {
        this.bookings = (ObservableList<Booking>) this.bookings.stream()
                .filter(b -> !b.hasExpired())
                .collect(Collectors.toList());
    }

    /**
     * Adds booking to the list of bookings.
     *
     * @param booking The booking to be added.
     * @throws BookingClashesException if the booking clashes with an existing booking.
     */
    public void addBooking(Booking booking) throws BookingClashesException {
        if (hasClashes(booking)) {
            throw new BookingClashesException();
        }
        this.bookings.add(booking);
    }

    /**
     * Checks whether a given booking has clashes with current bookings.
     *
     * @param booking The given booking to check against.
     * @return whether the given booking clashes with current bookings.
     */
    public boolean hasClashes(Booking booking) {
        requireNonNull(booking);
        for (Booking b : this.bookings) {
            if (b.clashesWith(booking)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the venues are the same
     */
    public boolean isSameVenue(Venue other) {
        return this.venueName.equals(other.venueName);
    }

    public boolean isSameVenue(VenueName otherVenueName) {
        return this.venueName.equals(otherVenueName);
    }

    public ObservableList<Booking> getObservableBookings() {
        clearExpiredBookings();
        return this.bookings;
    }

    @Override
    public String toString() {
        return this.venueName.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Venue
                && (this.venueName.equals(((Venue) other).venueName)
                && (this.bookings.equals(((Venue) other).bookings))));
    }

    // Todo: Populate venue table with the venues specified in the .txt file and hook up class with storage
}
