package bookface.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

import bookface.commons.core.Messages;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.book.Author;
import bookface.model.book.Book;
import bookface.model.book.Title;

/**
 * A utility class to help with building Book objects.
 */
public class BookBuilder {

    public static final String DEFAULT_TITLE = "How to Spell";

    public static final String DEFAULT_AUTHOR = "Ronald Johnson";

    private Title title;
    private Author author;

    private Date returnDate = new Date();
    /**
     * Creates a {@code BookBuilder} with the default details.
     */
    public BookBuilder() {
        title = new Title(DEFAULT_TITLE);
        author = new Author(DEFAULT_AUTHOR);
    }

    /**
     * Initializes the BookBuilder with the data of {@code bookToCopy}.
     */
    public BookBuilder(Book bookToCopy) {
        title = bookToCopy.getTitle();
        author = bookToCopy.getAuthor();
    }

    /**
     * Sets the {@code Title} of the {@code Book} that we are building.
     */
    public BookBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }


    /**
     * Sets the {@code Author} of the {@code Book} that we are building.
     */
    public BookBuilder withAuthor(String author) {
        this.author = new Author(author);
        return this;
    }


    public Book build() {
        return new Book(title, author);
    }

    public BookBuilder withReturnDate(String returnDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter = formatter.withResolverStyle(ResolverStyle.SMART);
        try {
            LocalDate ld = LocalDate.parse(returnDate, formatter);
            this.returnDate = java.sql.Date.valueOf(ld);
            return this;
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    public Book buildLoanedBook() {
        return new Book(title, author, returnDate);
    }
}
