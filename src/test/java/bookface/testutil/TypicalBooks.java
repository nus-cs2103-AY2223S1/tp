package bookface.testutil;

import static bookface.testutil.TypicalPersons.getSinglePerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bookface.model.BookFace;
import bookface.model.book.Book;

/**
 * A utility class containing a list of {@code Book} objects to be used in tests.
 */
public class TypicalBooks {

    public static final Book HOW_TO_SPELL = new BookBuilder().withTitle("How to Spell")
            .withAuthor("Ronald Johnson").build();
    public static final Book MAKING_A_COMPUTER = new BookBuilder().withTitle("Making a Computer")
            .withAuthor("Roger Smith").build();
    public static final Book GET_MOTIVATED = new BookBuilder().withTitle("Get Motivated")
            .withAuthor("Lim Chee Teck").build();
    public static final Book CRYING_LOUD_LOAN = new BookBuilder().withTitle("Crying loud")
            .withAuthor("Bill Smith").withReturnDate().build();
    public static final Book YES_MAN_LOAN = new BookBuilder().withTitle("Yes Man")
            .withAuthor("Iggy Teck").withReturnDate().build();

    private TypicalBooks() {} // prevents instantiation

    /**
     * Returns an {@code BookFace} with all the typical books.
     */
    public static BookFace getTypicalBookFaceData() {
        BookFace ab = new BookFace();
        for (Book book : getTypicalBooks()) {
            ab.addBook(book);
        }
        ab.addPerson(getSinglePerson());
        return ab;
    }

    public static List<Book> getTypicalBooks() {
        return new ArrayList<>(Arrays.asList(HOW_TO_SPELL, MAKING_A_COMPUTER, GET_MOTIVATED));
    }

    public static List<Book> getTypicalLoanedBook() {
        return new ArrayList<>(Arrays.asList(CRYING_LOUD_LOAN, YES_MAN_LOAN));
    }

    public static List<Book> getSingleBook() {
        return new ArrayList<>(List.of(HOW_TO_SPELL));
    }
}
