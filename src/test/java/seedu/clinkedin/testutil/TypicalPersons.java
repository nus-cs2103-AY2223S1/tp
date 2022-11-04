package seedu.clinkedin.testutil;

import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_RATING_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends").withStatus("Accepted").withNote("She is strong at Java.")
            .withRating("6").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
                    .withAddress("311, Clementi Ave 2, #02-25").withNote("He has a good sense of humour.")
            .withEmail("johnd@example.com").withPhone("98765432")
                    .withTags("owesMoney", "friends").withStatus("Rejected").withRating("0").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withStatus("OA Received")
            .withRating("4").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withStatus("Application Received").withRating("6").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withStatus("Application Received")
            .withRating("8").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withStatus("Application Received")
            .withRating("1").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withStatus("Application Received")
            .withRating("9").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withStatus("Application Received").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withStatus("Application Received").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withStatus(VALID_STATUS_AMY).withNote(VALID_NOTE_AMY).withRating(VALID_RATING_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withStatus(VALID_STATUS_BOB).withNote(VALID_NOTE_BOB).withRating(VALID_RATING_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
