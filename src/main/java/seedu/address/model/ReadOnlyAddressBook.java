package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.commission.Commission;
import seedu.address.model.person.Person;

import java.util.List;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Commission> getCommissionList();
}
