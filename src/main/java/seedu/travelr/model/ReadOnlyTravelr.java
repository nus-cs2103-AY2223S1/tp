package seedu.travelr.model;

import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Unmodifiable view of Travelr
 */
public interface ReadOnlyTravelr {

    /**
     * Returns an unmodifiable view of the trips list.
     * This list will not contain any duplicate trips.
     */
    ObservableList<Trip> getTripList();

    ObservableList<Event> getEventList();

    ObservableList<Event> getAllEventList();

}
