package seedu.travelr.model;

import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Trip> getTripList();

    ObservableList<Event> getEventList();

}
