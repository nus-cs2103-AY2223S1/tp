package modtrekt.model;

import javafx.collections.ObservableList;
import modtrekt.model.person.Person;
import modtrekt.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getPersonList();

}
