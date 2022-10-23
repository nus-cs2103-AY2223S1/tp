package seedu.rc4hdb.model;

import javafx.collections.ObservableList;
import seedu.rc4hdb.model.venues.Venue;

/**
 * Unmodifiable view of a venue book
 */
public interface ReadOnlyVenueBook {

    /**
     * Returns an unmodifiable view of the venues list.
     * This list will not contain any duplicate venues.
     */
    ObservableList<Venue> getVenueList();

}
