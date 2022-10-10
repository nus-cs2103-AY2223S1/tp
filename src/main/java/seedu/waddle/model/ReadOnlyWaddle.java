package seedu.waddle.model;

import javafx.collections.ObservableList;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyWaddle {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Itinerary> getItineraryList();

}
