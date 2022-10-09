package bookface.testutil;

import static bookface.testutil.TypicalPersons.getSinglePerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bookface.logic.commands.CommandTestUtil;
import bookface.model.BookFace;
import bookface.model.book.Book;
import bookface.model.person.Person;

/**
 * A utility class containing a list of {@code Book} objects to be used in tests.
 */
public class TypicalBooks {

    public static final Book HOWTOSPELL = new BookBuilder().withTitle("How to Spell")
            .withAuthor("Ronald Johnson").build();
    public static final Book MAKINGACOMPUTER = new BookBuilder().withTitle("Making a Computer")
            .withAuthor("Roger Smith").build();
    public static final Book GETMOTIVATED = new BookBuilder().withTitle("Get Motivated")
            .withAuthor("Lim Chee Teck").build();


    // Manually added - Book's details found in {@code CommandTestUtil}
    public static final Book HowToSpell = new BookBuilder().withTitle(CommandTestUtil.VALID_TITLE_HOW)
            .withAuthor(CommandTestUtil.VALID_AUTHOR_RONALD).build();
    public static final Book MakingAComputer = new BookBuilder().withTitle(CommandTestUtil.VALID_TITLE_MAKING)
            .withAuthor(CommandTestUtil.VALID_AUTHOR_ROGER).build();

    private TypicalBooks() {} // prevents instantiation

    /**
     * Returns an {@code BookFace} with all the typical books.
     */
    public static BookFace getTypicalBookFaceData() {
        BookFace ab = new BookFace();
        for (Book book : getTypicalBooks()) {
            ab.addBook(book);
        }
        for (Person person: getSinglePerson()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Book> getTypicalBooks() {
        return new ArrayList<>(Arrays.asList(HOWTOSPELL, MAKINGACOMPUTER, GETMOTIVATED));
    }

    public static List<Book> getSingleBook() {
        return new ArrayList<>(Arrays.asList(HOWTOSPELL));
    }
}
