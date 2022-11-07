package seedu.waddle.model;

import javafx.collections.ObservableList;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Unmodifiable view of a Waddle.
 */
public interface ReadOnlyWaddle {

    /**
     * Returns an unmodifiable view of the itinerary list.
     * This list will not contain any duplicate itineraries.
     */
    ObservableList<Itinerary> getItineraryList();

}
