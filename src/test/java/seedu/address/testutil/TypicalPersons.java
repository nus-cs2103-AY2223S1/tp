package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_2103;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withId("ce8e09f9-a330-4387-b862-2c5523264142")
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withRemark("free on monday")
            .withTags("CS2103T")
            .build();
    public static final Person BENSON = new PersonBuilder()
            .withId("1b75e80a-7f43-40b1-b576-f8e2b26a873c")
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withRemark("free on tuesday")
            .withTags("CS2103T", "CS2101")
            .build();
    public static final Person CARL = new PersonBuilder()
            .withId("d6c74f5b-c510-4838-a86f-d4bb22a7424")
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withRemark("free on wednesday")
            .build();
    public static final Person DANIEL = new PersonBuilder()
            .withId("1a442dad-8807-4ad6-a256-b63aff2c9175")
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withRemark("")
            .withTags("CS2102")
            .build();
    public static final Person ELLE = new PersonBuilder()
            .withId("bab841db-58a7-4db7-9271-45b3b641883e")
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withRemark("")
            .build();
    public static final Person FIONA = new PersonBuilder()
            .withId("f5a4c27e-3aa0-410f-8e4c-f8120630006b")
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withRemark("")
            .build();
    public static final Person GEORGE = new PersonBuilder()
            .withId("b8a3a593-2d0f-4a55-8345-a08f5ef14d5f")
            .withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withRemark("")
            .build();

    public static final Tag CS2103T = new TagBuilder().withName("CS2103T").withCount(2).build();
    public static final Tag CS2101 = new TagBuilder().withName("CS2101").withCount(1).build();
    public static final Tag CS2102 = new TagBuilder().withName("CS2102").withCount(1).build();


    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withId("caafeb13-997a-4e80-971b-d94b64f0a85e")
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withRemark("")
            .build();
    public static final Person IDA = new PersonBuilder()
            .withId("53b643f4-d024-45e2-9056-4d500ece7c30")
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withRemark("")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withId(VALID_ID_AMY)
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withRemark(VALID_REMARK_AMY)
            .withTags(VALID_TAG_2103)
            .build();
    public static final Person BOB = new PersonBuilder()
            .withId(VALID_ID_BOB)
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withRemark(VALID_REMARK_BOB)
            .withTags(VALID_TAG_2103, VALID_TAG_2101)
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
        for (Tag tag : getTypicalTags()) {
            ab.addTag(tag);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101, CS2102));
    }
}
