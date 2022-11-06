package bookface.model.book;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    private Date returnDate = null;

    /**
     * Every field must be present and not null. This is an overloaded constructor used in JsonAdaptedBook
     * if book does not have return date.
     */
    public Book(Title title, Author author) {
        CollectionUtil.requireAllNonNull(title, author);
        this.title = title;
        this.author = author;
    }

    /**
     * Every field must be present and not null. This is an overloaded constructor used in JsonAdaptedBook
     * if book has a return date.
     */
    public Book(Title title, Author author, Date returnDate) {
        CollectionUtil.requireAllNonNull(title, author, returnDate);
        this.title = title;
        this.author = author;
        this.returnDate = returnDate;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Optional<Date> getReturnDate() {
        return Optional.ofNullable(returnDate);
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public Optional<String> getReturnDateString() {
        return Optional.ofNullable(returnDate)
                .map(x -> "Return by: " + (new SimpleDateFormat("yyyy-MM-dd")).format(returnDate));
    }

    public Optional<Person> getLoanee() {
        return Optional.ofNullable(loanee);
    }

    /**
     * Checks if book is loaned out
     */
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
    public void loanTo(Person loanee, Date returnDate) {
        if (loanee != null) {
            this.loanee = loanee;
            this.returnDate = returnDate;
        }
    }


    /**
     * Return this loaned book.
     */
    public void markBookAsReturned() {
        this.loanee = null;
    }

    /**
     * Checks if the book is overdue.
     * @return True if book is overdue, false otherwise
     */
    public Optional<Boolean> isOverdue() {
        return this.getReturnDate().map((x) -> new Date().after(x));
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
        return otherBook.getTitle().equalsIgnoreCase(getTitle())
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
                + " | Author: "
                + getAuthor()
                + getReturnDateString().map(string -> " | " + string).orElse("");
    }
}
