package bookface.storage;

import static bookface.storage.JsonAdaptedBook.INVALID_RETURN_DATE_UNLOANED;
import static bookface.storage.JsonAdaptedBook.MISSING_BOOK_FIELD_MESSAGE_FORMAT;
import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TypicalBooks.CRYING_LOUD_LOAN;
import static bookface.testutil.TypicalBooks.HOW_TO_SPELL;
import static bookface.testutil.TypicalDates.INVALID_DATE_STRING;
import static bookface.testutil.TypicalDates.TYPICAL_DATE_STRING;
import static bookface.testutil.TypicalPersons.HILLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import bookface.commons.exceptions.IllegalValueException;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.book.Author;
import bookface.model.book.Title;

public class JsonAdaptedBookTest {
    private static final String INVALID_TITLE = " ";
    private static final String INVALID_AUTHOR = "R@chel";

    private static final String VALID_TITLE = HOW_TO_SPELL.getTitle().toString();
    private static final String VALID_AUTHOR = HOW_TO_SPELL.getAuthor().toString();

    private static final JsonAdaptedBook VALID_BOOK = new JsonAdaptedBook("The Deep Down",
            "Kerry Abiguidea", TYPICAL_DATE_STRING, true);
    private static final List<JsonAdaptedBook> VALID_LOANS = List.of(VALID_BOOK);
    private static final JsonAdaptedBook INVALID_BOOK = new JsonAdaptedBook("The Deep Down",
            "Kerry Abiguidea", TYPICAL_DATE_STRING, false);

    // test for unloaned book
    @Test
    public void toModelType_validUnloanedBookDetails_returnsBook() throws Exception {
        JsonAdaptedBook book = new JsonAdaptedBook(HOW_TO_SPELL);
        assertEquals(HOW_TO_SPELL, book.toModelType());
    }

    // test for loaned book
    @Test
    public void toModelType_validLoanedBookDetails_returnsBook() throws Exception {
        JsonAdaptedBook book = new JsonAdaptedBook(CRYING_LOUD_LOAN);
        assertTrue(HILLY.getLoanedBooksSet().contains(book.toModelType()));
    }

    // test for invalid title
    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(INVALID_TITLE, VALID_AUTHOR, TYPICAL_DATE_STRING, true);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    // test for null title
    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(null, VALID_AUTHOR, TYPICAL_DATE_STRING, true);
        String expectedMessage = String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    // test for invalid author
    @Test
    public void toModelType_invalidAuthor_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_TITLE, INVALID_AUTHOR, TYPICAL_DATE_STRING, true);
        String expectedMessage = Author.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    // test for null author
    @Test
    public void toModelType_nullAuthor_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_TITLE, null, TYPICAL_DATE_STRING, true);
        String expectedMessage = String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    // test for invalid date
    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, INVALID_DATE_STRING, true);
        assertThrows(ParseException.class, "Unparseable date: \"2022-2020202-09\"", book::toModelType);
    }

    // test for null date
    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, null, true);
        String expectedMessage = String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, "returnDate");
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    // test for invalid isLoaned
    @Test
    public void toModelType_invalidIsLoaned_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, TYPICAL_DATE_STRING, false);
        assertThrows(IllegalValueException.class, INVALID_RETURN_DATE_UNLOANED, book::toModelType);
    }

    // test for null isLoaned
    @Test
    public void toModelType_nullIsLoaned_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, TYPICAL_DATE_STRING, null);
        String expectedMessage = String.format(MISSING_BOOK_FIELD_MESSAGE_FORMAT, "isLoaned");
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }
}
