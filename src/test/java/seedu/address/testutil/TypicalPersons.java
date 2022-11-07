package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withModuleCode("CS2030S")
            .withEmail("alice@example.com")
            .withTelegram("@alicepauline")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withModuleCode("GEX1005")
            .withEmail("johnd@example.com")
            .withTelegram("@bensonmeier")
            .withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withTelegram("@carlkurz").withModuleCode("MA2001").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTelegram("@daniel")
            .withModuleCode("CS1101s").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withTelegram("@ellemeyer").withModuleCode("CS1010s").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withTelegram("@fiona").withModuleCode("GEA1000").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withTelegram("@george").withModuleCode("MA1521").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withTelegram("@hoonmeier").withModuleCode("cg2023").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withTelegram("@idamueller").withModuleCode("Ee2012").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY)
            .withModuleCode(VALID_MODULE_CODE_CS2100).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB).withModuleCode(VALID_MODULE_CODE_CS2103T)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
