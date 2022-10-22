package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the profiles list.
     * This list will not contain any duplicate profiles.
     */
    ObservableList<Profile> getProfileList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

}
