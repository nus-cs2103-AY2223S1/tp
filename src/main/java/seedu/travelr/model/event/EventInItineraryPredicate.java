package seedu.travelr.model.event;

import java.util.function.Predicate;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.list.Itineraries;

/**
 * Tests that an {@code Event}'s is found in the given trip's itinerary.
 */
public class EventInItineraryPredicate implements Predicate<Event> {

    private final Index index;
    private Itineraries itinerary;

    /**
     * Creates a new EventInItineraryPredicate.
     */
    public EventInItineraryPredicate(Index index) {
        this.index = index;
        this.itinerary = null;
    }

    /**
     * Sets the itinerary for this predicate.
     */
    public void setItinerary(Itineraries itinerary) {
        this.itinerary = itinerary;
    }

    /**
     * Returns the Index of this Predicate.
     */
    public Index getIndex() {
        return index;
    }

    @Override
    public boolean test(Event event) {
        return itinerary.contains(event);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventInItineraryPredicate // instanceof handles nulls
                && index.equals(((EventInItineraryPredicate) other).index)); // state check
    }
}
