package bookface.model.book;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import bookface.commons.util.CollectionUtil;
import bookface.model.book.exceptions.DuplicateBookException;
import bookface.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The BookList class represents the list of books managed by BookFace.
 */
public class BookList extends ArrayList<Book> implements Iterable<Book> {
    private ArrayList<Book> bookList = new ArrayList<>();

    private final ObservableList<Book> internalList = FXCollections.observableArrayList();
    //private final ObservableList<Book> internalList = FXCollections.observableArrayList(bookList); doesnt work?
    private final ObservableList<Book> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Constructs a BookList
     */
    public BookList() {}


    /**
     * Returns true if the list contains an equivalent book as the given argument.
     */
    public boolean contains(Book toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBook);
    }

    /**
     * Gets whether BookList contains a certain object.
     *
     * @param o element whose presence in this list is to be tested
     * @return true if object is in BookList
     */
    @Override
    public boolean contains(Object o) {
        return this.bookList.contains(o);
    }

    /**
     * Gets the size of the BookList.
     *
     * @return size of BookList
     */
    @Override
    public int size() {
        return this.bookList.size();
    }

    /**
     * Adds a book to the BookList.
     *
     * @param book element whose presence in this collection is to be ensured
     * @return true if book is added successfully, false otherwise
     */
    @Override
    public boolean add(Book book) {
        requireNonNull(book);
        if (contains(book)) {
            throw new DuplicateBookException();
        }
        internalList.add(book);
        return this.bookList.add(book);
    }

    /**
     * Empties the BookList.
     */
    @Override
    public void clear() {
        this.bookList.clear();
    }

    /**
     * Removes book at specified index from BookList.
     *
     * @param index the index of the element to be removed
     * @return the removed book
     */
    @Override
    public Book remove(int index) {
        return this.bookList.remove(index);
    }

    /**
     * Gets the book at specified index.
     *
     * @param index index of the element to return
     * @return book at specified index
     */
    @Override
    public Book get(int index) {
        return this.bookList.get(index);
    }


    public ObservableList<Book> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    /**
     * Replaces the contents of this list with {@code books}.
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
     * Returns true if {@code books} contains only unique books.
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
        book.loanTo(person);
    }
}
