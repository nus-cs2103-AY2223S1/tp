package swift.testutil;

import static swift.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static swift.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static swift.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static swift.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static swift.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static swift.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static swift.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static swift.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static swift.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static swift.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import swift.model.AddressBook;
import swift.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    public static final String ALICE_UUID = "c4c645da-27b3-454d-9428-5295a6ee1f33";
    public static final String BENSON_UUID = "42d68acf-f3db-4c55-bbe6-4a3d1db64ac0";
    public static final String CARL_UUID = "6a5f8ed7-2607-47d1-9faf-bf2437642985";
    public static final String DANIEL_UUID = "fc3ad432-f7ed-403f-9236-18054ddd4b31";
    public static final String ELLE_UUID = "dbef9f04-d31a-42b4-b57a-4d2fd34444c3";
    public static final String FIONA_UUID = "79faf217-b266-4dd8-91a5-d801e87e5447";
    public static final String GEORGE_UUID = "14cec028-25a3-466d-8ffc-359dc1e2a585";

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").withId(ALICE_UUID).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withId(BENSON_UUID).build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withId(CARL_UUID).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withId(DANIEL_UUID).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withId(ELLE_UUID).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withId(FIONA_UUID).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withId(GEORGE_UUID).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
