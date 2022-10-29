package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * Unmodifiable view of a NUScheduler
 */
public interface ReadOnlyNuScheduler {

    /**
     * Returns an unmodifiable view of the profiles list.
     * This list will not contain any profiles with the same email, phone or telegram.
     */
    ObservableList<Profile> getProfileList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

}
