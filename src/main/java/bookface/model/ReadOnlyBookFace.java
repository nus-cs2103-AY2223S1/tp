package bookface.model;

import bookface.model.book.Book;
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

<<<<<<< HEAD
    /**
     * Returns an unmodifiable view of the books list.
     * This list will not contain any duplicate books.
     */
    ObservableList<Person> getBookList();
=======
    ObservableList<Book> getBookList();
>>>>>>> 1507ab73c1b4f2330f4a8197027c39be601377bf
}
