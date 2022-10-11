package bookface.model.book;

import static java.util.Objects.requireNonNull;

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
        return internalList.stream().anyMatch(toCheck::isSameBook);
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
     * Removes the book specified from BookList.
     * The book must exist in the list.
     */
    public void delete(Book book) {
        requireNonNull(book);
        if (!internalList.remove(book)) {
            throw new BookNotFoundException();
        }
    }

    public void setBooks(BookList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code books}
     * {@code books} must not contain duplicate persons.
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
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean booksAreUnique(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(i).isSameBook(books.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Loans to a person {@code person} a book {@code book} .
     */
    public void loan(Person person, Book book) {
        CollectionUtil.requireAllNonNull(person, book);
        book.loanTo(person, book);
        //Book loanedBook = new Book(book.getTitle(), book.getAuthor(), "Loaned to " + person.getName());
        int index = internalList.indexOf(book);
        internalList.set(index, book);
        //internalList.set(index, loanedBook);
    }

    /**
     * TODO: Empties the BookList.
     */
    public void clear() {}
}
