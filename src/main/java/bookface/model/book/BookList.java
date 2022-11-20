package bookface.model.book;

import static java.util.Objects.requireNonNull;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import bookface.commons.util.CollectionUtil;
import bookface.model.book.exceptions.BookNotFoundException;
import bookface.model.book.exceptions.DuplicateBookException;
import bookface.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The BookList class represents the list of books managed by BookFace.
 */
public class BookList implements Iterable<Book> {
    private final ObservableList<Book> internalList = FXCollections.observableArrayList();
    //private final ObservableList<Book> internalList = FXCollections.observableArrayList(bookList); //doesnt work?
    private final ObservableList<Book> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent book as the given argument.
     */
    public boolean contains(Book toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a book to the BookList.
     * The book must not already exist in the list.
     */
    public void add(Book book) {
        requireNonNull(book);
        if (contains(book)) {
            throw new DuplicateBookException();
        }
        internalList.add(book);
    }

    /**
     * Replaces the book {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in the list.
     * The book identity of {@code editedBook} must not be the same as another existing book in the list.
     */
    public void setBook(Book target, Book editedBook) {
        CollectionUtil.requireAllNonNull(target, editedBook);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookNotFoundException();
        }

        if (!target.equals(editedBook) && contains(editedBook)) {
            throw new DuplicateBookException();
        }

        internalList.set(index, editedBook);
    }

    /**
     * Removes the book specified from BookList.
     * The book must exist in the list.
     */
    public void delete(Book book) {
        requireNonNull(book);
        if (!internalList.remove(book)) {
            throw new BookNotFoundException();
        }
    }

    /**
     * Refreshes the book list after an operation on a {@code person}, such as an edit or delete operation.
     */
    public void refreshBookListAfterDeletingPerson(Person person) {
        requireNonNull(person);
        for (Book book : person.getLoanedBooksSet()) {
            book.markBookAsReturned();
            int index = internalList.indexOf(book);
            internalList.set(index, book);
        }
    }

    /**
     * Refreshes the book list after an operation on a {@code person}, such as an edit or delete operation.
     */
    public void refreshBookListAfterEditingPerson(Person person) {
        requireNonNull(person);
        for (Book book : person.getLoanedBooksSet()) {
            int index = internalList.indexOf(book);
            internalList.set(index, book);
        }
    }

    public void setBooks(BookList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code books}
     * {@code books} must not contain duplicate books.
     */
    public void setBooks(List<Book> books) {
        CollectionUtil.requireAllNonNull(books);
        if (!booksAreUnique(books)) {
            throw new DuplicateBookException();
        }
        internalList.setAll(books);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Book> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Book> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof BookList
                && internalList.equals(((BookList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code books} contains only unique books.
     */
    private boolean booksAreUnique(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(i).equals(books.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Loans to a person {@code person} a book {@code book} with the given {@code returnDate}.
     */
    public void loan(Person person, Book book, Date returnDate) {
        CollectionUtil.requireAllNonNull(person, book, returnDate);
        book.loanTo(person, returnDate);
        int index = internalList.indexOf(book);
        internalList.set(index, book);
    }

    /**
     * Returns loan of a book {@code book} .
     */
    public void returnLoanedBook(Book book) {
        CollectionUtil.requireAllNonNull(book);
        book.markBookAsReturned();
        int index = internalList.indexOf(book);
        internalList.set(index, book);
    }
}
