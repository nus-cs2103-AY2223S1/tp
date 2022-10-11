package jeryl.fyp.testutil;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECTNAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECTNAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jeryl.fyp.model.AddressBook;
import jeryl.fyp.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withStudentID("A1351253H").withProjectName("neural network")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withStudentID("A1765432D")
            .withProjectName("decision tree")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withStudentID("A1352563C")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withProjectName("B+ Tree").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withStudentID("A1652533D")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withProjectName("Soft Engineering").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withStudentID("A1482224E")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withProjectName("database design").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withStudentID("A1482427F")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withProjectName("rasterization in game development").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withStudentID("A1482442G")
            .withEmail("anna@example.com").withAddress("4th street")
            .withProjectName("programming language").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withStudentID("A1482424H")
            .withEmail("stefan@example.com").withAddress("little india")
            .withProjectName("Robotics").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withStudentID("A1482131I")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withProjectName("computer network").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withStudentID(VALID_STUDENTID_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withProjectName(VALID_PROJECTNAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withStudentID(VALID_STUDENTID_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withProjectName(VALID_PROJECTNAME_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

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
