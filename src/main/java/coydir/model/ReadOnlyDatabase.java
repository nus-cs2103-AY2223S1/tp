package coydir.model;

import coydir.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an database
 */
public interface ReadOnlyDatabase {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
