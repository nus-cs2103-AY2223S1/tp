package seedu.address.testutil;

import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.PersonCommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withPersonId(0)
            .withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withCompany("Meta")
            .build();
    public static final Person BENSON = new PersonBuilder()
            .withPersonId(1)
            .withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withCompany("Meta")
            .build();
    public static final Person CARL = new PersonBuilder()
            .withPersonId(2)
            .withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com")
            .withCompany("Meta")
            .build();
    public static final Person DANIEL = new PersonBuilder()
            .withPersonId(3)
            .withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withTags("friends")
            .withCompany("Meta")
            .build();
    public static final Person ELLE = new PersonBuilder()
            .withPersonId(4)
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withCompany("Meta")
            .build();
    public static final Person FIONA = new PersonBuilder()
            .withPersonId(5)
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withCompany("Meta")
            .build();
    public static final Person GEORGE = new PersonBuilder()
            .withPersonId(6)
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withCompany("Meta")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .build();
    public static final Person IDA = new PersonBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .build();

    // Manually added - Person's details found in {@code PersonCommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withCompany(VALID_COMPANY_AMY)
            .build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withCompany(VALID_COMPANY_BOB)
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
