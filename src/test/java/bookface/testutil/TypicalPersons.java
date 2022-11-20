package bookface.testutil;

import static bookface.testutil.TypicalBooks.getSingleBook;
import static bookface.testutil.TypicalBooks.getTypicalBooks;
import static bookface.testutil.TypicalBooks.getTypicalLoanedBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bookface.logic.commands.CommandTestUtil;
import bookface.model.BookFace;
import bookface.model.book.Book;
import bookface.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();
    public static final Person HILLY = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withBooksToLoan(getTypicalLoanedBook()).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
            .withPhone(CommandTestUtil.VALID_PHONE_AMY)
            .withEmail(CommandTestUtil.VALID_EMAIL_AMY).withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
            .withPhone(CommandTestUtil.VALID_PHONE_BOB)
            .withEmail(CommandTestUtil.VALID_EMAIL_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND,
                    CommandTestUtil.VALID_TAG_FRIEND)
            .build();


    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code BookFace} with all the typical persons and a typical book.
     */
    public static BookFace getTypicalBookFaceData() {
        BookFace ab = new BookFace();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Book book : getSingleBook()) {
            ab.addBook(book);
        }
        return ab;
    }

    /**
     * Returns an {@code BookFace} with all the typical persons and all typical books.
     */
    public static BookFace getAllTypicalBookFaceData() {
        BookFace ab = new BookFace();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Book book : getTypicalBooks()) {
            ab.addBook(book);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static Person getSinglePerson() {
        return ALICE;
    }
}
