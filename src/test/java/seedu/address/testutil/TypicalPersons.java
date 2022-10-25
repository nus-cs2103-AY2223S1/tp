package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("ABC Pte Ltd")
            .withAddress("123, Jurong West Ave 6, #08-111").withPrice("$1.50")
            .withItem("Chicken").withPhone("67089005")
            .withTags("Supplier").build();
    public static final Person ALICE_SUPPLIER = new PersonBuilder().withName("ABC Pte Ltd")
            .withAddress("123, Jurong West Ave 6, #08-111").withPrice("$1.50")
            .withItem("Ginger").withPhone("67089005")
            .build();

    public static final Person BENSON = new PersonBuilder().withName("Ya Shu Egg")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPrice("$3.50").withItem("Egg").withPhone("63450864")
            .withTags("Supplier").build();
    public static final Person BENSON_SUPPLIER = new PersonBuilder().withName("Ya Shu Egg")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPrice("$3.50").withItem("Beef").withPhone("63450864")
            .build();

    public static final Person CARL = new PersonBuilder().withName("KyEggs").withPhone("61240985")
            .withPrice("$0.90").withItem("Egg").withAddress("Blk 11 Ang Mo Kio Street 74, #11-04").build();
    public static final Person CARL_SUPPLIER = new PersonBuilder().withName("KyEggs").withPhone("61240985")
            .withPrice("$0.90").withItem("Lamb").withAddress("Blk 11 Ang Mo Kio Street 74, #11-04").build();

    public static final Person DANIEL = new PersonBuilder().withName("Goh Supplies").withPhone("69008045")
            .withPrice("$3.80").withItem("Tonic Water")
            .withAddress("10th street").withTags("Supplier").build();
    public static final Person DANIEL_SUPPLIER = new PersonBuilder().withName("Goh Supplies").withPhone("69008045")
            .withPrice("$3.80").withItem("Spinach")
            .withAddress("10th street").build();

    public static final Person ELLE = new PersonBuilder().withName("Soho Singapore").withPhone("64300567")
            .withPrice("$0.60").withItem("Napkins").withAddress("michegan ave").build();

    public static final Person FIONA = new PersonBuilder().withName("Balas Market").withPhone("62624417")
            .withPrice("$1.10").withItem("Cups").withAddress("little tokyo").build();

    public static final Person GEORGE = new PersonBuilder().withName("Best Supplies").withPhone("65409876")
            .withPrice("$0.80").withItem("Noodles").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withPrice("$2.10").withItem("Chopsticks").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withPrice("$1.40").withItem("Tofu").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withPrice(VALID_PRICE_AMY).withItem(VALID_ITEM_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withPrice(VALID_PRICE_BOB).withItem(VALID_ITEM_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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

    /**
     * Returns an {@code AddressBook} with all the typical suppliers.
     */
    public static AddressBook getTypicalAddressBookWithSuppliers() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalSuppliers()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getTypicalSuppliers() {
        return new ArrayList<>(Arrays.asList(ALICE_SUPPLIER, BENSON_SUPPLIER, CARL_SUPPLIER, DANIEL_SUPPLIER));
    }
}
