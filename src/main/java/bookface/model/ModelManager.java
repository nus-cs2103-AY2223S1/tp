package bookface.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import bookface.commons.core.GuiSettings;
import bookface.commons.core.LogsCenter;
import bookface.commons.util.CollectionUtil;
import bookface.model.book.Book;
import bookface.model.person.Person;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of BookFace data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BookFace bookFace;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Book> filteredBooks;

    /**
     * Initializes a ModelManager with the given bookFace and userPrefs.
     */
    public ModelManager(ReadOnlyBookFace bookFace, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(bookFace, userPrefs);

        logger.fine("Initializing with BookFace: " + bookFace + " and user prefs " + userPrefs);

        this.bookFace = new BookFace(bookFace);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.bookFace.getPersonList());
        filteredBooks = new FilteredList<>(this.bookFace.getBookList());
    }

    public ModelManager() {
        this(new BookFace(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getBookFaceFilePath() {
        return userPrefs.getBookFaceFilePath();
    }

    @Override
    public void setBookFaceFilePath(Path bookFaceFilePath) {
        requireNonNull(bookFaceFilePath);
        userPrefs.setBookFaceFilePath(bookFaceFilePath);
    }

    //=========== BookFace ================================================================================

    @Override
    public void setBookFace(ReadOnlyBookFace bookFace) {
        this.bookFace.resetData(bookFace);
    }

    @Override
    public ReadOnlyBookFace getBookFace() {
        return bookFace;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return bookFace.hasPerson(person);
    }

    @Override
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return bookFace.hasBook(book);
    }

    @Override
    public void deletePerson(Person target) {
        bookFace.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        bookFace.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addBook(Book book) {
        bookFace.addBook(book);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
    }

    @Override
    public void deleteBook(Book book) {
        bookFace.deleteBook(book);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        CollectionUtil.requireAllNonNull(target, editedPerson);
        bookFace.setPerson(target, editedPerson);
    }

    @Override
    public void loan(Person person, Book book) {
        CollectionUtil.requireAllNonNull(person, book);
        bookFace.loan(person, book);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
    }

    @Override
    public void returnLoan(Book book) {
        CollectionUtil.requireAllNonNull(book);
        bookFace.returnLoan(book);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedBookFace}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedBookFace}
     */
    @Override
    public ObservableList<Book> getFilteredBookList() {
        return filteredBooks;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredBookList(Predicate<Book> predicate) {
        requireNonNull(predicate);
        filteredBooks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return bookFace.equals(other.bookFace)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredBooks.equals(other.filteredBooks);
    }

}
