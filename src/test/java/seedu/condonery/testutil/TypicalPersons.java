package seedu.condonery.testutil;

import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_ADDRESS_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_ADDRESS_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_EMAIL_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_EMAIL_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_NAME_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_NAME_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_PHONE_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_PHONE_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_TAG_FRIEND;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.condonery.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPhone("94351253")
        .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withEmail("johnd@example.com").withPhone("98765432")
        .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
        .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
        .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
        .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
        .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY =
            new PersonBuilder().withName(CLIENT_VALID_NAME_AMY)
                    .withPhone(CLIENT_VALID_PHONE_AMY)
                    .withEmail(CLIENT_VALID_EMAIL_AMY)
                    .withAddress(CLIENT_VALID_ADDRESS_AMY)
                    .withTags(CLIENT_VALID_TAG_FRIEND).build();
    public static final Person BOB =
            new PersonBuilder().withName(CLIENT_VALID_NAME_BOB)
                    .withPhone(CLIENT_VALID_PHONE_BOB)
                    .withEmail(CLIENT_VALID_EMAIL_BOB)
                    .withAddress(CLIENT_VALID_ADDRESS_BOB)
                    .withTags(CLIENT_VALID_TAG_HUSBAND, CLIENT_VALID_TAG_FRIEND)
                    .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
