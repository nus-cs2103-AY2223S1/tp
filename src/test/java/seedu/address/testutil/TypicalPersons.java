package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATESLOT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATESLOT_BOB;
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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;

/**
 * A utility class containing a list of {@code Patients / Nurses} objects to be
 * used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withUid("1").withCategory("P")
            .withName("Alice Pauline").withGender("F")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withDatesSlots("2022-11-11,2")
            .withTags("friends")
            .withAttendingPhysician("John Doe", "81234567", "johndoe@example.com").build();

    public static final Person BENSON = new PersonBuilder()
            .withUid("2").withCategory("P")
            .withName("Benson Meier").withGender("M")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withDatesSlots("2022-10-23,1")
            .withTags("owesMoney", "friends")
            .withAttendingPhysician("John Doe", "81234567", "johndoe@example.com").build();
    public static final Person CARL = new PersonBuilder()
            .withUid("3").withCategory("P")
            .withName("Carl Kurz").withGender("M")
            .withPhone("95352563")
            .withDatesSlots("2022-11-10,1", "2022-12-10,2")
            .withEmail("heinz@example.com")
            .withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder()
            .withUid("4").withCategory("P")
            .withName("Daniel Meier")
            .withGender("M")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withDatesSlots("2022-11-10,4", "2022-09-10,2")
            .withAttendingPhysician("John Doe", "81234567", "johndoe@example.com")
            .withAddress("10th street")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder()
            .withUid("5").withCategory("N")
            .withName("Elle Meyer").withGender("F")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withDatesSlots("2022-11-15,4")
            .withAddress("michegan ave")
            .withHomeVisits("2022-10-15,1:6")
            .withUnavailableDateList("2022-11-11")
            .withFullyScheduledDateList().build();
    public static final Person FIONA = new PersonBuilder()
            .withUid("6").withCategory("P")
            .withName("Fiona Kunz")
            .withGender("F")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withDatesSlots("2022-10-15,1")
            .withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder()
            .withUid("7")
            .withCategory("P")
            .withName("George Best")
            .withGender("M")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withUid("8").withCategory("P")
            .withName("Hoon Meier")
            .withGender("M")
            .withPhone("8482424")
            .withDatesSlots("2023-01-11,2")
            .withEmail("stefan@example.com")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder()
            .withUid("9")
            .withCategory("P")
            .withName("Ida Mueller")
            .withGender("F")
            .withPhone("8482131")
            .withDatesSlots("2022-11-11,3")
            .withEmail("hans@example.com")
            .withAddress("chicago ave").build();

    public static final NextOfKin JADON = new NextOfKin(
            new Name("Jadon Sacho"),
            new Phone("81234567"),
            new Email("jadon@example.com"));

    public static final Physician HOUSE = new Physician(
            new Name("House"),
            new Phone("91234567"),
            new Email("house@example.com"));

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withUid(VALID_UID_AMY).withCategory(VALID_CATEGORY_AMY).withName(VALID_NAME_AMY)
            .withGender(VALID_GENDER_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withDatesSlots((VALID_DATESLOT_AMY)).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder()
            .withUid(VALID_UID_BOB).withCategory(VALID_CATEGORY_BOB).withName(VALID_NAME_BOB)
            .withGender(VALID_GENDER_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withDatesSlots(VALID_DATESLOT_BOB)
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
