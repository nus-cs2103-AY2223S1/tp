package seedu.rc4hdb.model;

import javafx.collections.ObservableList;
import seedu.rc4hdb.model.resident.Resident;

/**
 * Unmodifiable view of a resident book
 */
public interface ReadOnlyResidentBook {

    /**
     * Returns an unmodifiable view of the residents list.
     * This list will not contain any duplicate residents.
     */
    ObservableList<Resident> getResidentList();

}
