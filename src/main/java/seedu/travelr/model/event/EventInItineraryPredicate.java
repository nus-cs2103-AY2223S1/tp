package seedu.travelr.model.event;

import javafx.collections.transformation.FilteredList;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.list.Itineraries;

import java.util.List;
import java.util.function.Predicate;

public class EventInItineraryPredicate implements Predicate<Event> {

    private final Index index;
    private Itineraries itinerary;

    public EventInItineraryPredicate(Index index) {
        this.index = index;
        this.itinerary = null;
    }

    public EventInItineraryPredicate(Index index, Itineraries itinerary) {
        this.index = index;
        this.itinerary = itinerary;
    }

    public void setItinerary(Itineraries itinerary) {
        this.itinerary = itinerary;
    }

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
