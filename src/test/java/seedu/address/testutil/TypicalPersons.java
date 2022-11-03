package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.user.User;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final User ZEPHYR = new UserBuilder()
            .withName("Zephyr Yosef")
            .withAddress("333, Yishun St 33, #03-33")
            .withPhone("90123456")
            .withEmail("zephYosef@example.com")
            .withGithub("ZephYosef")
            .withCurrentModules("CS2103T")
            .withPreviousModules("CS2040S")
            .withPlannedModules("CS2109S")
            .build();

    public static final User XAVIER = new UserBuilder()
            .withName("Xavier Williams")
            .withAddress("555, Ang Mo Kio St 55, #05-55")
            .withPhone("96543210")
            .withEmail("xavWilliams@example.com")
            .withGithub("Xavier-Williams")
            .withCurrentModules("CS2103T")
            .withPreviousModules("CS2040S")
            .withPlannedModules("CS2109S")
            .build();

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withGithub("alice-pauline")
            .withTags("family")
            .withCurrentModules("CS2103T")
            .withPreviousModules("CS2040S")
            .withPlannedModules("CS2109S")
            .build();
    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withGithub("BensonMeier")
            .withTags("owesMoney", "friends")
            .withCurrentModules("CS2103T")
            .withPreviousModules("CS2040S")
            .withPlannedModules("CS2109S")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withGithub("Kurz-Carl")
            .withCurrentModules("CS2101")
            .withPreviousModules("CS2030S")
            .withPlannedModules("CS2105")
            .withLessons(new LessonBuilder().build())
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withGithub("danMeier")
            .withTags("friends")
            .withCurrentModules("CS2101")
            .withPreviousModules("CS2030S")
            .withPlannedModules("CS2105")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withGithub("elleeee")
            .withCurrentModules("CS2101")
            .withPreviousModules("CS2030S")
            .withPlannedModules("CS2105")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withGithub("fiona-kunz")
            .withCurrentModules("CS2101")
            .withPreviousModules("CS2030S")
            .withPlannedModules("CS2105")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withGithub("George-the-b3st")
            .withCurrentModules("CS2103T")
            .withPreviousModules("CS2040S")
            .withPlannedModules("CS2109S")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withGithub("hoonhoon")
            .withCurrentModules("CS2101")
            .withPreviousModules("CS2030S")
            .withPlannedModules("CS2105")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withGithub("idamueller")
            .withCurrentModules("CS2101")
            .withPreviousModules("CS2030S")
            .withPlannedModules("CS2105")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withGithub(VALID_GITHUB_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withGithub(VALID_GITHUB_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    public static final User ZAC = new UserBuilder()
            .withName(VALID_NAME_ZAC)
            .withPhone(VALID_PHONE_ZAC)
            .withEmail(VALID_EMAIL_ZAC)
            .withAddress(VALID_ADDRESS_ZAC)
            .withGithub(VALID_GITHUB_ZAC)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addUser(ZEPHYR);
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
