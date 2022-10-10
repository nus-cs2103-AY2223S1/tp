package bookface.model;

import java.nio.file.Path;
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
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setBookFace(ReadOnlyBookFace bookFace);

    /** Returns the AddressBook */
    ReadOnlyBookFace getBookFace();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
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
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Loans to given person {@code person} from {@code book}.
     * {@code person} and {@code book} must exist in the address book.
     *
     */
    void loan(Person person, Book book);

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
