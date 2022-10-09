package seedu.travelr.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.travelr.model.trip.exceptions.DuplicateTripException;
import seedu.travelr.model.trip.exceptions.TripNotFoundException;

/**
 * A list of trips that enforces uniqueness between its elements and does not allow nulls.
 * A trip is considered unique by comparing using {@code Trip#isSameTrip(Trip)}. As such, adding and updating of
 * trips uses Trip#isSameTrip(Trip) for equality to ensure that the trip being added or updated is
 * unique in terms of identity in the UniqueTripList. However, the removal of a person uses Trip#equals(Object)
 * to ensure that the trip with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Trip#isSameTrip(Trip)
 */
public class UniqueTripList implements Iterable<Trip> {

    private final ObservableList<Trip> internalList = FXCollections.observableArrayList();
    private final ObservableList<Trip> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Trip toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTrip);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Trip toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTripException();
        }
        internalList.add(toAdd);
    }

    public int getIndexOfTrip(Trip trip) {
        if (contains(trip)) {
            for (int i = 0; i < internalList.size(); i++) {
                if (internalList.get(i).equals(trip)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Trip getTrip(Trip trip) {
        return internalList.get(getIndexOfTrip(trip));
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setTrip(Trip target, Trip editedTrip) {
        requireAllNonNull(target, editedTrip);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TripNotFoundException();
        }

        if (!target.isSameTrip(editedTrip) && contains(editedTrip)) {
            throw new DuplicateTripException();
        }

        internalList.set(index, editedTrip);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Trip toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TripNotFoundException();
        }
    }

    public void setTrips(UniqueTripList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code trips}.
     * {@code trips} must not contain duplicate trips.
     */
    public void setTrips(List<Trip> trips) {
        requireAllNonNull(trips);
        if (!tripsAreUnique(trips)) {
            throw new DuplicateTripException();
        }

        internalList.setAll(trips);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Trip> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Trip> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTripList // instanceof handles nulls
                        && internalList.equals(((UniqueTripList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code trips} contains only unique trips.
     */
    private boolean tripsAreUnique(List<Trip> trips) {
        for (int i = 0; i < trips.size() - 1; i++) {
            for (int j = i + 1; j < trips.size(); j++) {
                if (trips.get(i).isSameTrip(trips.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
