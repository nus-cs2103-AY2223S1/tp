package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withBirthday("02-02-2000")
            .withHealthInsurance(false).withDisabilityInsurance(false)
            .withCriticalIllnessInsurance(false).withLifeInsurance(false)
            .withReminders(new Pair<>("remind alice", "01-01-2000")).withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withBirthday("01-12-1999")
            .withTags("owesMoney", "friends")
            .withHealthInsurance(false).withDisabilityInsurance(true)
            .withCriticalIllnessInsurance(false).withLifeInsurance(true)
            .withReminders(new Pair<>("remind benson", "01-01-2001")).build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withReminders(new Pair<>("remind carl", "01-01-2002"))
            .withBirthday("20-03-2010").withAddress("wall street")
            .withHealthInsurance(true).withDisabilityInsurance(true)
            .withCriticalIllnessInsurance(true).withLifeInsurance(false).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withBirthday("01-11-2000").withAddress("10th street").withTags("friends")
            .withHealthInsurance(false).withDisabilityInsurance(false)
            .withReminders(new Pair<>("remind daniel", "01-01-2003"))
            .withCriticalIllnessInsurance(false).withLifeInsurance(false).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withReminders(new Pair<>("remind elle", "01-01-2004"))
            .withBirthday("01-10-2000").withAddress("michegan ave")
            .withHealthInsurance(true).withDisabilityInsurance(true)
            .withCriticalIllnessInsurance(true).withLifeInsurance(true).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withReminders(new Pair<>("remind fiona", "01-02-2001"))
            .withBirthday("01-01-2000").withAddress("little tokyo")
            .withHealthInsurance(true).withDisabilityInsurance(false)
            .withCriticalIllnessInsurance(true).withLifeInsurance(false).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com")
            .withBirthday("01-01-2000").withAddress("4th street")
            .withReminders(new Pair<>("remind george", "02-01-2001"))
            .withHealthInsurance(true).withDisabilityInsurance(false)
            .withCriticalIllnessInsurance(false).withLifeInsurance(false).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com")
            .withBirthday("01-05-2000").withAddress("little india")
            .withHealthInsurance(true).withDisabilityInsurance(true)
            .withReminders(new Pair<>("remind hoon", "01-01-1999"))
            .withCriticalIllnessInsurance(false).withLifeInsurance(true).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withBirthday("10-07-2000")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withHealthInsurance(false).withDisabilityInsurance(false)
            .withReminders(new Pair<>("remind ida", "02-02-1999"))
            .withCriticalIllnessInsurance(true).withLifeInsurance(false).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withBirthday(VALID_BIRTHDAY_AMY).withReminders(new Pair<>("remind amy", "01-01-2001")).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withBirthday(VALID_BIRTHDAY_BOB).withReminders(new Pair<>("remind bob", "01-01-2001")).build();

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
