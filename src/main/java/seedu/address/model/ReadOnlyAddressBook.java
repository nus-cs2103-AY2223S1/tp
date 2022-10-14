package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.profile.Profile;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the profiles list.
     * This list will not contain any similar profiles. Similar profiles are profiles with similar names or
     * similar emails.
     */
    ObservableList<Profile> getProfileList();

}
