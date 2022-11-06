package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.teammate.Teammate;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the teammates list.
     * This list will not contain any duplicate teammates.
     */
    ObservableList<Teammate> getTeammateList();

}
