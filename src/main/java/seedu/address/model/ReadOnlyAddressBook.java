package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the medications list.
     * This list will not contain any duplicate medications.
     */
    String getMedicationMap();

    /**
     * Returns a string representation of the address book's census.
     */
    String getCensus();

    /**
     * Returns a string representation of the address book's medication map.
     */
    String getStringifiedMedicationMap();
}
