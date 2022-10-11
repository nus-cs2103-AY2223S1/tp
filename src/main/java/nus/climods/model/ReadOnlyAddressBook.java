package nus.climods.model;

import javafx.collections.ObservableList;
import nus.climods.model.person.Person;
import nus.climods.model.module.UserModule;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list. This list will not contain any duplicate persons.
     *
     * can be deleted later.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the module list. This list will not contain any duplicate modules.
     */
    ObservableList<UserModule> getUserModuleList();
}
