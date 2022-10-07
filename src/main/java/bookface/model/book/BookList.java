package bookface.model.book;

import java.util.ArrayList;

/**
 * The BookList class represents the list of books managed by BookFace.
 */
public class BookList extends ArrayList<Book> {
    private ArrayList<Book> bookList = new ArrayList<>();

    /**
     * Constructs a BookList
     */
    public BookList() {}

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
}
