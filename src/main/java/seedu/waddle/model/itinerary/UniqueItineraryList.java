package seedu.waddle.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.waddle.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.waddle.model.itinerary.exceptions.ItineraryNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Itinerary#isSameItinerary(Itinerary)
 */
public class UniqueItineraryList implements Iterable<Itinerary> {

    private final ObservableList<Itinerary> internalList = FXCollections.observableArrayList();
    private final ObservableList<Itinerary> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Itinerary toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameItinerary);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Itinerary toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItineraryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        requireAllNonNull(target, editedItinerary);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItineraryNotFoundException();
        }

        if (!target.isSameItinerary(editedItinerary) && contains(editedItinerary)) {
            throw new DuplicateItineraryException();
        }

        internalList.set(index, editedItinerary);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Itinerary toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItineraryNotFoundException();
        }
    }

    public void setItineraries(UniqueItineraryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setItineraries(List<Itinerary> itineraries) {
        requireAllNonNull(itineraries);
        if (!itinerariesAreUnique(itineraries)) {
            throw new DuplicateItineraryException();
        }

        internalList.setAll(itineraries);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Itinerary> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Itinerary> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueItineraryList // instanceof handles nulls
                        && internalList.equals(((UniqueItineraryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean itinerariesAreUnique(List<Itinerary> itineraries) {
        for (int i = 0; i < itineraries.size() - 1; i++) {
            for (int j = i + 1; j < itineraries.size(); j++) {
                if (itineraries.get(i).isSameItinerary(itineraries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
