package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.entry.Entry;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPennyWise {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Entry> getEntryList();

}
