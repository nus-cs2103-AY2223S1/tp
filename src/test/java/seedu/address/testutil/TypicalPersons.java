package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.ContactType;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111")
        .withTags("friends")
        .withTimezone("+8")
        .withRole("Software Engineer")
        .withContact(ContactType.TELEGRAM, "@alice")
        .withContact(ContactType.EMAIL, "alice@gmail.com").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withTags("owesMoney", "friends")
        .withTimezone("+8")
        .withRole("Product Manager")
        .withContact(ContactType.TELEGRAM, "@benson")
        .withContact(ContactType.EMAIL, "benson@gmail.com").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withAddress("wall street").build();
    public static final Person DANIEL =
        new PersonBuilder().withName("Daniel Meier").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withAddress("little india").build();

    public static final Person JOON = new PersonBuilder().withName("Joon Smith")
            .withAddress("little india").withRole("Backend Engineer")
            .withGithubUser("sh4nH").withTags("friends").build();

    public static final Person FANG = new PersonBuilder().withName("Lee Fang").withAddress("clementi ave")
            .withRole("Frontend Engineer").withGithubUser("george").withTags("friend").build();

    public static final Person LEE = new PersonBuilder().withName("Lee Chan")
            .withAddress("little china").withTags("friend").build();


    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withRole(VALID_ROLE_AMY).build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withRole(VALID_ROLE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

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

    public static AddressBook getTypicalAddressBookForFindTest() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersonsForFindTest()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getTypicalPersonsForFindTest() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, JOON, LEE, FANG));
    }
}
