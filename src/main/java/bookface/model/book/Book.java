package bookface.model.book;

import java.util.Objects;
import java.util.Optional;

import bookface.commons.util.CollectionUtil;
import bookface.model.person.Person;


/**
 * Represents a Book in the BookFace application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Book {
    private final Title title;
    private final Author author;
    private Person loanee = null;

    /**
     * Every field must be present and not null.
     */
    public Book(Title title, Author author) {
        CollectionUtil.requireAllNonNull(title, author);
        this.title = title;
        this.author = author;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Optional<Person> getLoanee() {
        return Optional.ofNullable(loanee);
    }

    public boolean isLoaned() {
        return this.loanee != null;
    }

    public String getLoanStatus() {
        if (isLoaned()) {
            return "Loaned to " + loanee.getName();
        } else {
            return "Available";
        }
    }

    /**
     * Loans this book to a patron.
     *
     * @param loanee the person borrowing this book
     */
    public void loanTo(Person loanee) {
        if (loanee != null) {
            this.loanee = loanee;
        }
    }

    /**
     * Return this loaned book .
     */
    public void markBookAsReturned() {
        this.loanee = null;
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
        return getTitle()
                + "; Author: "
                + getAuthor();
    }
}
