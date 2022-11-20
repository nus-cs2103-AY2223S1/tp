package bookface.storage;

import static bookface.commons.util.Date.DATE_FORMAT;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bookface.commons.exceptions.IllegalValueException;
import bookface.commons.util.StringUtil;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.book.Author;
import bookface.model.book.Book;
import bookface.model.book.Title;

/**
 * Jackson-friendly version of {@link Book}.
 */
class JsonAdaptedBook {

    public static final String INVALID_RETURN_DATE_UNLOANED = "An unloaned Book's returnDate field is not the empty "
            + "string!";
    public static final String MISSING_BOOK_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";
    public static final String INVALID_BOOK_FORMAT = "Invalid format for a loaned book detected!";

    private final String title;
    private final String author;

    private final String returnDate;

    private final Boolean isLoaned;

    /**
     * Constructs a {@code JsonAdaptedBook} with the given book details.
     */
    @JsonCreator
    public JsonAdaptedBook(@JsonProperty("title") String title, @JsonProperty("author") String author,
                           @JsonProperty("returnDate") String returnDate, @JsonProperty("isLoaned") Boolean isLoaned) {
        this.title = title;
        this.author = author;
        this.returnDate = returnDate;
        this.isLoaned = isLoaned;
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     */
    public JsonAdaptedBook(Book source) {
        title = source.getTitle().bookTitle;
        author = source.getAuthor().bookAuthor;
        isLoaned = source.isLoaned();
        if (isLoaned) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            returnDate = formatter.format(source.getReturnDate()
                    .orElseGet(bookface.commons.util.Date::getFourteenDaysLaterDate));
        } else {
            returnDate = "";
        }
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Book toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(
                    String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (author == null) {
            throw new IllegalValueException(
                    String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName()));
        }
        if (!Author.isValidAuthor(author)) {
            throw new IllegalValueException(Author.MESSAGE_CONSTRAINTS);
        }
        final Author modelAuthor = new Author(author);

        if (isLoaned == null) {
            throw new IllegalValueException(String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, "isLoaned"));
        }

        if (returnDate == null) {
            throw new IllegalValueException(String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, "returnDate"));
        }

        if (!isLoaned && !StringUtil.containsWhitespaceOnly(returnDate)) {
            throw new IllegalValueException(INVALID_RETURN_DATE_UNLOANED);
        }

        if (isLoaned && StringUtil.containsWhitespaceOnly(returnDate)) {
            throw new IllegalValueException("A loaned Book's returnDate field is the empty string!");
        }

        if (isLoaned) {
            try {
                final Date modelDate = DATE_FORMAT.parse(returnDate);
                if (returnDate.length() != 10) {
                    throw new ParseException("A loaned Book's returnDate field does not have exactly 10 characters!");
                }
                return new Book(modelTitle, modelAuthor, modelDate);
            } catch (java.text.ParseException pe) {
                throw new ParseException(pe.getMessage());
            }
        } else {
            return new Book(modelTitle, modelAuthor);
        }
    }
}
