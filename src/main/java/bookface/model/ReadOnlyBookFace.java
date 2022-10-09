package bookface.model;

import bookface.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyBookFace {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the books list.
     * This list will not contain any duplicate books.
     */
    ObservableList<Person> getBookList();
}
