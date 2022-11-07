package bookface.model;

import bookface.model.book.Book;
import bookface.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of BookFace
 */
public interface ReadOnlyBookFace {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Book> getBookList();
}
