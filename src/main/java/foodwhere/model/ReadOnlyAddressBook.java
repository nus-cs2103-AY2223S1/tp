package foodwhere.model;

import foodwhere.model.stall.Stall;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the stalls list.
     * This list will not contain any duplicate stalls.
     */
    ObservableList<Stall> getStallList();

}
