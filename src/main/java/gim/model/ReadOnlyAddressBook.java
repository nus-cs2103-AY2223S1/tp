package gim.model;

import javafx.collections.ObservableList;
import gim.model.person.Exercise;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Exercise> getExerciseList();

}
