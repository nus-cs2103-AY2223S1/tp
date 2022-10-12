package bookface.model.book;

import java.util.Objects;

import bookface.commons.util.CollectionUtil;
import bookface.model.person.Person;


/**
 * Represents a Book in the BookFace application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Book {

    // Identity fields
    private final Title title;
    private final Author author;
    private boolean isLoaned = false;
    private Person loanee = null;

    private final String loanStatus;



    /**
     * Every field must be present and not null.
     */
    /*
    public Book(Title title, Author author) {
        CollectionUtil.requireAllNonNull(title, author);
        this.title = title;
        this.author = author;
    }
    */


    /**
     * Every field must be present and not null.
     */

    public Book(Title title, Author author, String loanStatus) {
        CollectionUtil.requireAllNonNull(title, author);
        this.title = title;
        this.author = author;
        this.loanStatus = loanStatus;
    }


    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isLoaned() {
        return this.isLoaned;
    }

    public String getLoanStatus() {
        boolean temp = isLoaned;
        if (temp) {
            return "Loaned to " + loanee.getName();
        } else {
            return "Available";
        }
    }




    /**
     * Sets the book's isLoaned status to false and loanee to null.
     * i.e. Collects a loaned book.
     */
    public void collect() {
        this.isLoaned = false;
        this.loanee = null;
    }

    /**
     * Loans this book to a patron.
     *
     * @param loanee the person borrowing this book
     */
    public void loanTo(Person loanee, Book book) {
        this.isLoaned = true;
        this.loanee = loanee;
    }

    /**
     * Returns true if both books have the same title.
     * This defines a weaker notion of equality between two books.
     */
    public boolean isSameBook(Book otherBook) {
        if (otherBook == this) {
            return true;
        }

        return otherBook != null
                && otherBook.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both books have the same details.
     * This defines a stronger notion of equality between two books.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Book)) {
            return false;
        }

        Book otherBook = (Book) other;
        return otherBook.getTitle().equals(getTitle())
                && otherBook.getAuthor().equals(getAuthor());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Author: ")
                .append(getAuthor());

        return builder.toString();
    }
}
