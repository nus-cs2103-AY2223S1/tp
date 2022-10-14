package seedu.clinkedin.model;

import javafx.collections.ObservableList;
import seedu.clinkedin.model.person.Person;

/**
 * Unmodifiable view of an clinkedin book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns the number of persons in the clinkedin book.
     */
    int getCount();
}
