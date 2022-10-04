package seedu.guest.model;

import javafx.collections.ObservableList;
import seedu.guest.model.guest.Guest;

/**
 * Unmodifiable view of a guest book
 */
public interface ReadOnlyGuestBook {

    /**
     * Returns an unmodifiable view of the guests list.
     * This list will not contain any duplicate guests.
     */
    ObservableList<Guest> getGuestList();

}
