package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Person;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Buyer} objects to be used in tests.
 */
public class TypicalBuyers {

    public static final Buyer ALICE = new PersonBuilder().withPersonCategory("Buyer").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").buildBuyer();
    public static final Buyer BENSON = new PersonBuilder().withPersonCategory("Buyer").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").buildBuyer();
    public static final Buyer CARL = new PersonBuilder().withPersonCategory("Buyer").withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street").buildBuyer();
    public static final Buyer DANIEL = new PersonBuilder().withPersonCategory("Buyer").withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .buildBuyer();
    public static final Buyer ELLE = new PersonBuilder().withPersonCategory("Buyer").withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave").buildBuyer();
    public static final Buyer FIONA = new PersonBuilder().withPersonCategory("Buyer").withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo").buildBuyer();
    public static final Buyer GEORGE = new PersonBuilder().withPersonCategory("Buyer").withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street").buildBuyer();

    // Manually added
    public static final Person HOON = new PersonBuilder().withPersonCategory("Buyer").withName("Hoon Meier")
            .withPhone("8482424").withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withPersonCategory("Buyer").withName("Ida Mueller")
            .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withPersonCategory(VALID_PERSON_CATEGORY_AMY)
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withPersonCategory(VALID_PERSON_CATEGORY_BOB)
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    public static final Person CAL = new PersonBuilder().withPersonCategory(VALID_PERSON_CATEGORY_CAL)
            .withName(VALID_NAME_CAL).withPhone(VALID_PHONE_CAL).withEmail(VALID_EMAIL_CAL)
            .withAddress(VALID_ADDRESS_CAL).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBuyers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical buyers.
     */
    public static AddressBook getTypicalBuyerAddressBook() {
        AddressBook ab = new AddressBook();
        for (Buyer buyer : getTypicalBuyers()) {
            ab.addBuyer(buyer);
        }
        return ab;
    }

    public static List<Buyer> getTypicalBuyers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}