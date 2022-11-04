package seedu.waddle.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.UniqueItineraryList;

/**
 * Wraps all data at the Waddle level
 * Duplicates are not allowed (by .isSameItinerary comparison)
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
     * Creates a Waddle using the Itineraries in the {@code toBeCopied}
     */
    public Waddle(ReadOnlyWaddle toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the itinerary list with {@code itineraries}.
     * {@code itineraries} must not contain duplicate itineraries.
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries.setItineraries(itineraries);
    }

    /**
     * Resets the existing data of this {@code Waddle} with {@code newData}.
     */
    public void resetData(ReadOnlyWaddle newData) {
        requireNonNull(newData);

        setItineraries(newData.getItineraryList());
    }

    //// itinerary-level operations

    /**
     * Returns true if an itinerary with the same identity as {@code itinerary} exists in Waddle.
     */
    public boolean hasItinerary(Itinerary itinerary) {
        requireNonNull(itinerary);
        return itineraries.contains(itinerary);
    }

    /**
     * Adds an Itinerary to Waddle.
     * The itinerary must not already exist in Waddle.
     */
    public void addItinerary(Itinerary p) {
        itineraries.add(p);
    }

    /**
     * Replaces the given itinerary {@code target} in the list with {@code editedItinerary}.
     * {@code target} must exist in Waddle.
     * The itinerary identity of {@code editedItinerary} must not be the same as
     * another existing itinerary in Waddle.
     */
    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        requireNonNull(editedItinerary);

        itineraries.setItinerary(target, editedItinerary);
    }

    /**
     * Removes {@code key} from this {@code Waddle}.
     * {@code key} must exist in Waddle.
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
