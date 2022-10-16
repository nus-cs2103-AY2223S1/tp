package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Patients / Nurses} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withUid("1").withCategory("P")
            .withName("Alice Pauline").withGender("F")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withDatesTimes("2022-11-11T12:00")
            .withTags("friends")
            .withVisitStatus("false").build();
    public static final Person BENSON = new PersonBuilder()
            .withUid("2").withCategory("P")
            .withName("Benson Meier").withGender("M")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withDatesTimes("2022-10-23T10:00")
            .withTags("owesMoney", "friends")
            .withVisitStatus("true").build();
    public static final Person CARL = new PersonBuilder()
            .withUid("3").withCategory("P")
            .withName("Carl Kurz").withGender("M")
            .withPhone("95352563")
            .withDatesTimes("2022-11-10T15:00", "2022-12-10T14:00")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withVisitStatus("false").build();
    public static final Person DANIEL = new PersonBuilder()
            .withUid("4").withCategory("P")
            .withName("Daniel Meier")
            .withGender("M")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withDatesTimes("2022-11-10T15:00", "2022-09-10T14:00")
            .withAddress("10th street")
            .withTags("friends")
            .withVisitStatus("true").build();
    public static final Person ELLE = new PersonBuilder()
            .withUid("5").withCategory("N")
            .withName("Elle Meyer").withGender("F")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withDatesTimes("2022-11-15T12:00")
            .withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder()
            .withUid("6").withCategory("P")
            .withName("Fiona Kunz")
            .withGender("F")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withDatesTimes("2022-10-15T12:00")
            .withAddress("little tokyo")
            .withVisitStatus("false").build();
    public static final Person GEORGE = new PersonBuilder()
            .withUid("7")
            .withCategory("P")
            .withName("George Best")
            .withGender("M")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withVisitStatus("true").build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withUid("8").withCategory("P")
            .withName("Hoon Meier")
            .withGender("M")
            .withPhone("8482424")
            .withDatesTimes("2023-01-11T18:00")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withVisitStatus("false").build();
    public static final Person IDA = new PersonBuilder()
            .withUid("9")
            .withCategory("P")
            .withName("Ida Mueller")
            .withGender("F")
            .withPhone("8482131")
            .withDatesTimes("2022-11-11T17:00")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withVisitStatus("false").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withUid(VALID_UID_AMY).withCategory(VALID_CATEGORY_AMY).withName(VALID_NAME_AMY)
            .withGender(VALID_GENDER_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withDatesTimes((VALID_DATETIME_AMY)).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder()
            .withUid(VALID_UID_BOB).withCategory(VALID_CATEGORY_BOB).withName(VALID_NAME_BOB)
            .withGender(VALID_GENDER_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withDatesTimes(VALID_DATETIME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
