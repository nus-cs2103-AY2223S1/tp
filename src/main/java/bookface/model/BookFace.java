package bookface.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import bookface.commons.util.CollectionUtil;
import bookface.model.book.Book;
import bookface.model.book.BookList;
import bookface.model.person.Person;
import bookface.model.person.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class BookFace implements ReadOnlyBookFace {

    private final UniquePersonList persons;
    private final BookList books;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        books = new BookList();
    }

    public BookFace() {}

    /**
     * Creates an BookFace using the Persons in the {@code toBeCopied}
     */
    public BookFace(ReadOnlyBookFace toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setBooks(List<Book> books) {
        this.books.setBooks(books);
    }

    /**
     * Resets the existing data of this {@code BookFace} with {@code newData}.
     */
    public void resetData(ReadOnlyBookFace newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setBooks(newData.getBookList());
    }



    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in BookFace.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if book is already in the book list.
     *
     * @param book the book to search
     * @return true if in book list, false otherwise
     */
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return books.contains(book);
    }

    /**
     * Adds a person to BookFace.
     * The person must not already exist in BookFace.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a book to BookFace's book list.
     *
     * @param book the book to add.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Deletes a book from BookFace's book list.
     *
     * @param book the book to delete.
     */
    public void deleteBook(Book book) {
        books.delete(book);
        persons.refreshUserListAfterDeletingBook(book);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in BookFace.
     * The person identity of {@code editedPerson} must not be the same as another existing person in BookFace.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Loans to the person {@code person} in the user list with the book {@code book} in the book list.
     * {@code person} and {@code book} must exist in BookFace.
     */
    public void loan(Person person, Book book) {
        CollectionUtil.requireAllNonNull(person, book);
        if (book.isLoaned()) {
            return;
        }
        books.loan(person, book);
        persons.loan(person, book);
    }

    /**
     * Loans to the person {@code person} in the user list with the book {@code book} in the book list.
     * {@code person} and {@code book} must exist in BookFace.
     */
    public void returnLoanedBook(Book book) {
        CollectionUtil.requireAllNonNull(book);
        if (!book.isLoaned()) {
            return;
        }
        books.returnLoanedBook(book);
        persons.returnLoanedBook(book);
    }

    /**
     * Removes {@code key} from this {@code BookFace}.
     * {@code key} must exist in BookFace.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        books.refreshBookListAfterDeletingUser(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Book> getBookList() {
        return books.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookFace // instanceof handles nulls
                && persons.equals(((BookFace) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
