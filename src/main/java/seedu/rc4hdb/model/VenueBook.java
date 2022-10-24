package seedu.rc4hdb.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.rc4hdb.model.venues.UniqueVenueList;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * Wraps all venue data at the RC4HDB level
 * Duplicates are not allowed (by .isSameVenue comparison)
 */
public class VenueBook implements ReadOnlyVenueBook {

    private final UniqueVenueList venues;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        venues = new UniqueVenueList();
    }

    public VenueBook() {}

    /**
     * Creates a VenueBook using the venues in the {@code toBeCopied}
     */
    public VenueBook(ReadOnlyVenueBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //================= list overwrite operations ===================================

    /**
     * Replaces the contents of the venue list with {@code venues}.
     * {@code venues} must not contain duplicate venues.
     */
    public void setVenues(List<Venue> venues) {
        this.venues.setVenues(venues);
    }

    /**
     * Resets the existing data of this {@code VenueBook} with {@code newData}.
     */
    public void resetData(ReadOnlyVenueBook newData) {
        requireNonNull(newData);
        setVenues(newData.getVenueList());
    }

    //================== venue-level operations =====================================

    /**
     * Returns true if a venue with the same identity as {@code venue} exists in the venue book.
     */
    public boolean hasVenue(Venue venue) {
        requireNonNull(venue);
        return venues.contains(venue);
    }

    /**
     * Adds a venue to the venue book.
     * The venue must not already exist in the venue book.
     */
    public void addVenue(Venue p) {
        venues.add(p);
    }

    /**
     * Removes {@code key} from this {@code VenueBook}.
     * {@code key} must exist in the venue book.
     */
    public void removeVenue(Venue key) {
        venues.remove(key);
    }

    /**
     * Adds a booking to the venue in the list with the name {@code venueName}.
     * @throws VenueNotFoundException if the venue does not exist in the list.
     */
    public void addBooking(VenueName venueName, Booking booking)
            throws VenueNotFoundException, BookingClashesException {
        venues.addBooking(venueName, booking);
    }

    /**
     * Removes a booking corresponding to {@code bookedPeriod} and {@code bookedDay} from the venue in the list with
     * the name {@code venueName}.
     * @throws VenueNotFoundException if the venue does not exist in the list.
     * @throws BookingNotFoundException if the venue is not booked during the specified period and day.
     */
    public void removeBooking(VenueName venueName, HourPeriod bookingPeriod , Day bookedDay)
            throws VenueNotFoundException, BookingNotFoundException {
        venues.removeBooking(venueName, bookingPeriod, bookedDay);
    }

    //// util methods

    @Override
    public String toString() {
        return venues.asUnmodifiableObservableList().size() + " venues";
        // TODO: refine later
    }

    public ObservableList<Venue> getVenueList() {
        return venues.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VenueBook // instanceof handles nulls
                && venues.equals(((VenueBook) other).venues));
    }

    @Override
    public int hashCode() {
        return venues.hashCode();
    }

}
