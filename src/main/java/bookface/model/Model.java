package bookface.model;

import java.nio.file.Path;
import java.util.Date;
import java.util.function.Predicate;

import bookface.commons.core.GuiSettings;
import bookface.model.book.Book;
import bookface.model.person.Person;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Book> PREDICATE_SHOW_ALL_BOOKS = unused -> true;

    /** {@code Predicate} returns true if person loaned a book*/
    Predicate<Person> PREDICATE_ALL_LOANEES = Person::hasBooksOnLoan;

    /** {@code Predicate} returns true if book is loaned*/
    Predicate<Book> PREDICATE_ALL_LOANED_BOOKS = Book::isLoaned;

    /** {@code Predicate} returns true for all persons with overdue books */
    Predicate<Person> PREDICATE_OWE_OVERDUE_BOOKS = person -> {
        if (person.hasBooksOnLoan()) {
            for (Book book : person.getLoanedBooksSet()) {
                if (book.isOverdue().orElse(false)) {
                    return true;
                }
            }
        }
        return false;
    };

    /** {@code Predicate} returns true for all books due past return date */
    Predicate<Book> PREDICATE_ALL_OVERDUE_BOOKS = book -> {
        if (book.isLoaned()) {
            return book.isOverdue().orElse(false);
        }
        return false;
    };

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' BookFace file path.
     */
    Path getBookFaceFilePath();

    /**
     * Sets the user prefs' BookFace file path.
     */
    void setBookFaceFilePath(Path bookFaceFilePath);

    /**
     * Replaces BookFace data with the data in {@code bookFace}.
     */
    void setBookFace(ReadOnlyBookFace bookFace);

    /** Returns the BookFace */
    ReadOnlyBookFace getBookFace();

    /**
     * Returns true if a person with the same identity as {@code person} exists in BookFace.
     */
    boolean hasPerson(Person person);

    /**
     * Gets whether the book exists in BookFace's book list.
     *
     * @param book a book to search in the booklist
     * @return true if the book exists, false otherwise.
     */
    boolean hasBook(Book book);

    /**
     * Deletes the given person.
     * The person must exist in BookFace.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in BookFace.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in BookFace.
     * The person identity of {@code editedPerson} must not be the same as another existing person in BookFace.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given book {@code target} with {@code editedBook}.
     * {@code target} must exist in BookFace.
     * The book identity of {@code editedPerson} must not be the same as another existing book in BookFace.
     */
    void setBook(Book target, Book editedBook);


    /**
     * Loans to given person {@code person} from {@code book}.
     * {@code person} and {@code book} must exist in BookFace.
     */
    void loan(Person person, Book book, Date returnDate);

    /**
     * Returns the {@code book} loan.
     */
    void returnLoanedBook(Book book);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered book list */
    ObservableList<Book> getFilteredBookList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Adds a book to BookFace records.
     *
     * @param book a book to add to BookFace.
     */
    void addBook(Book book);

    /**
     * Deletes a book from BookFace records.
     * @param book book to be deleted from BookFace
     */
    void deleteBook(Book book);

    void updateFilteredBookList(Predicate<Book> predicate);
}
