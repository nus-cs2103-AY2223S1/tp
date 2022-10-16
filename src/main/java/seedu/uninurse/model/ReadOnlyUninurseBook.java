package seedu.uninurse.model;

import javafx.collections.ObservableList;
import seedu.uninurse.model.person.Patient;

/**
 * Unmodifiable view of an uninurse book
 */
public interface ReadOnlyUninurseBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Patient> getPersonList();

}
