package bookface.testutil;

import static bookface.testutil.TypicalPersons.getSinglePerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bookface.logic.parser.exceptions.ParseException;
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

    public static final Book HOW_TO_RUN;

    static {
        try {
            HOW_TO_RUN = new BookBuilder().withTitle("How to Run")
                    .withAuthor("Ronald Dickson").withReturnDate().buildLoanedBook();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Book HOW_TO_EAT;

    static {
        try {
            HOW_TO_EAT = new BookBuilder().withTitle("How to Eat")
                    .withAuthor("Ronald Vickson").withReturnDate().buildLoanedBook();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Book HOW_TO_CRY;

    static {
        try {
            HOW_TO_CRY = new BookBuilder().withTitle("How to Cry")
                    .withAuthor("Ronald Pickson").withReturnDate().buildLoanedBook();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



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

    public static List<Book> getTypicalLoanedBooks() {
        return new ArrayList<>(Arrays.asList(HOW_TO_RUN, HOW_TO_EAT, HOW_TO_CRY));
    }

    public static List<Book> getSingleBook() {
        return new ArrayList<>(List.of(HOW_TO_SPELL));
    }
}
