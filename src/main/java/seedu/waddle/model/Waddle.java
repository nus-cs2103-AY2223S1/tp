package seedu.waddle.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.UniqueItineraryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Waddle implements ReadOnlyWaddle {

    private final UniqueItineraryList itineraries;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        itineraries = new UniqueItineraryList();
    }

    public Waddle() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public Waddle(ReadOnlyWaddle toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries.setItineraries(itineraries);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyWaddle newData) {
        requireNonNull(newData);

        setItineraries(newData.getItineraryList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasItinerary(Itinerary itinerary) {
        requireNonNull(itinerary);
        return itineraries.contains(itinerary);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addItinerary(Itinerary p) {
        itineraries.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        requireNonNull(editedItinerary);

        itineraries.setItinerary(target, editedItinerary);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeItinerary(Itinerary key) {
        itineraries.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return itineraries.asUnmodifiableObservableList().size() + " itineraries";
        // TODO: refine later
    }

    @Override
    public ObservableList<Itinerary> getItineraryList() {
        return itineraries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Waddle // instanceof handles nulls
                && itineraries.equals(((Waddle) other).itineraries));
    }

    @Override
    public int hashCode() {
        return itineraries.hashCode();
    }
}
