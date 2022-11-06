package seedu.rc4hdb.model.venues;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.exceptions.DuplicateVenueException;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * A map of venues that enforces uniqueness between its elements and does not allow nulls. A venue is considered unique
 * by comparing using {@code Venue#isSameVenue(Venue)}. As such, adding and updating of venues uses
 * Venue#isSameVenue(Venue) for equality so as to ensure that the venue being added or updated is unique in terms of
 * identity in the UniqueVenueList. However, the removal of a venue uses Venue#equals(Object) so as to ensure that the
 * venue with exactly the same fields will be removed.
 *
 * Supports a minimal set of map operations.
 *
 * @see Venue#isSameVenue(Venue)
 */
public class UniqueVenueList implements Iterable<Venue> {

    private final ObservableList<Venue> internalList = FXCollections.observableArrayList();
    private final ObservableList<Venue> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the map contains an equivalent venue as the given argument.
     */
    public boolean contains(Venue toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameVenue);
    }

    /**
     * Adds a venue to the list. The venue must not already exist in the list.
     */
    public void add(Venue toAdd) throws DuplicateVenueException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateVenueException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent venue from the list.
     * The venue must exist in the list.
     */
    public void remove(Venue toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new VenueNotFoundException();
        }
    }

    /**
     * Similar to
     * @see #remove(Venue) but uses {@code VenueName} as an identifier.
     */
    public void remove(VenueName toRemove) {
        requireNonNull(toRemove);
        if (!internalList.removeIf(venue -> venue.isSameVenue(toRemove))) {
            throw new VenueNotFoundException();
        }
    }

    /**
     * Adds a booking to the venue in the list with the name {@code venueName}.
     * @throws VenueNotFoundException if the venue does not exist in the list.
     */
    public void addBooking(VenueName otherVenueName, Booking booking)
            throws VenueNotFoundException, BookingClashesException {
        requireAllNonNull(otherVenueName, booking);
        getVenueWithName(otherVenueName).addBooking(booking);
    }

    /**
     * Removes a booking corresponding to the {@code bookingDescriptor}. Booking descriptor must minimally have
     * a VenueName, HourPeriod and Day.
     * @throws VenueNotFoundException if the venue does not exist in the list.
     * @throws BookingNotFoundException if the venue is not booked during the specified period and day.
     */
    public void removeBooking(BookingDescriptor bookingDescriptor)
            throws VenueNotFoundException, BookingNotFoundException {
        requireNonNull(bookingDescriptor);
        if (bookingDescriptor.getVenueName().isEmpty()) {
            throw new VenueNotFoundException();
        }
        getVenueWithName(bookingDescriptor.getVenueName().get())
                .removeBooking(bookingDescriptor);
    }

    public void setVenues(UniqueVenueList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code venues}.
     * {@code venues} must not contain duplicate venues.
     */
    public void setVenues(List<Venue> venues) {
        requireAllNonNull(venues);
        if (!venuesAreUnique(venues)) {
            throw new DuplicateVenueException();
        }

        internalList.setAll(venues);
    }

    /**
     * Gets the venue in {@code venues} has the same name as {@code venueName}.
     * @see Venue#isSameVenue(VenueName)
     */
    public Venue getVenueWithName(VenueName venueName) throws VenueNotFoundException {
        requireNonNull(venueName);
        for (Venue venue : internalList) {
            if (venue.isSameVenue(venueName)) {
                return venue;
            }
        }
        throw new VenueNotFoundException();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Venue> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Venue> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueVenueList // instanceof handles nulls
                && internalList.equals(((UniqueVenueList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code venues} contains only unique venues.
     */
    private boolean venuesAreUnique(List<Venue> venues) {
        for (int i = 0; i < venues.size() - 1; i++) {
            for (int j = i + 1; j < venues.size(); j++) {
                if (venues.get(i).isSameVenue(venues.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
