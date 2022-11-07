package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OCCUPATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OCCUPATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_BOB;
import static seedu.address.logic.commands.SocialTestUtil.getAmySocial;
import static seedu.address.logic.commands.SocialTestUtil.getBobSocial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.social.Social;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withOccupation("STUDENT")
            .withName("Alice Pauline").withPhone("94351253").withEmail("alice@example.com")
            .withTutorial("T08").withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends").withGroups("friends").withSocial(new Social()).build();
    public static final Person BENSON = new PersonBuilder().withOccupation("STUDENT")
            .withName("Benson Meier").withPhone("98765432").withEmail("johnd@example.com")
            .withTutorial("T09").withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").withGroups("friends").withSocial(new Social()).build();
    public static final Person CARL = new PersonBuilder().withOccupation("STUDENT")
            .withName("Carl Kurz").withPhone("95352563").withEmail("heinz@example.com")
            .withTutorial("T10").withAddress("wall street").withSocial(new Social()).build();
    public static final Person DANIEL = new PersonBuilder().withOccupation("STUDENT")
            .withName("Daniel Meier").withPhone("87652533").withEmail("cornelia@example.com")
            .withTutorial("T11").withAddress("10th street")
            .withTags("friends").withGroups("friends").withSocial(new Social()).build();
    public static final Person ELLE = new PersonBuilder().withOccupation("STUDENT")
            .withName("Elle Meyer").withPhone("9482224").withEmail("werner@example.com")
            .withTutorial("T12").withAddress("michegan ave").withSocial(new Social()).build();
    public static final Person FIONA = new PersonBuilder().withOccupation("STUDENT")
            .withName("Fiona Kunz").withPhone("9482427").withEmail("lydia@example.com")
            .withTutorial("T13").withAddress("little tokyo").withSocial(new Social()).build();
    public static final Person GEORGE = new PersonBuilder().withOccupation("STUDENT")
            .withName("George Best").withPhone("9482442").withEmail("anna@example.com")
            .withTutorial("F08").withAddress("4th street").withSocial(new Social()).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withOccupation("STUDENT")
            .withPhone("8482424").withEmail("stefan@example.com").withTutorial("T08")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withOccupation("TA")
            .withPhone("8482131").withEmail("hans@example.com").withTutorial("T08")
            .withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY =
            new PersonBuilder().withOccupation(VALID_OCCUPATION_AMY).withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withTutorial(VALID_TUTORIAL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withOccupation(VALID_OCCUPATION_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTutorial(VALID_TUTORIAL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

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

    public static AddressBook getSocialAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addPerson(new PersonBuilder().withOccupation(VALID_OCCUPATION_AMY).withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTutorial(VALID_TUTORIAL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).withSocial(getAmySocial()).build());
        ab.addPerson(new PersonBuilder().withName(VALID_NAME_BOB).withOccupation(VALID_OCCUPATION_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTutorial(VALID_TUTORIAL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withSocial(getBobSocial()).build());
        return ab;
    }
}
