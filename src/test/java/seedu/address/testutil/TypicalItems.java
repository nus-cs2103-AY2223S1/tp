package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.item.Item;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item POTATOES = new ItemBuilder()
        .withItemName("Potatoes")
        .withItemQuantity("10")
        .withItemUnit("kg")
        .withItemBoughtDate("2022-11-11")
        .withItemExpiryDate("2022-11-11").build();

    public static final Item CUCUMBERS = new ItemBuilder()
        .withItemName("Cucumbers")
        .withItemQuantity("2000")
        .withItemUnit("grams")
        .withItemBoughtDate("2022-11-11")
        .withItemExpiryDate("2022-11-11").build();

    //    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
    //            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
    //            .withPhone("94351253")
    //            .withTags("friends").build();
    //    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
    //            .withAddress("311, Clementi Ave 2, #02-25")
    //            .withEmail("johnd@example.com").withPhone("98765432")
    //            .withTags("owesMoney", "friends").build();
    //    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
    //            .withEmail("heinz@example.com").withAddress("wall street").build();
    //    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
    //            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    //    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
    //            .withEmail("werner@example.com").withAddress("michegan ave").build();
    //    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
    //            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    //    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
    //            .withEmail("anna@example.com").withAddress("4th street").build();
    //
    //    // Manually added
    //    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
    //            .withEmail("stefan@example.com").withAddress("little india").build();
    //    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
    //            .withEmail("hans@example.com").withAddress("chicago ave").build();
    //
    //    // Manually added - Person's details found in {@code CommandTestUtil}
    //    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
    //            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    //    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
    //            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
    //            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItems() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook addressBook = new AddressBook();
        for (Item item : getTypicalItems()) {
            addressBook.addItem(item);
        }
        return addressBook;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(POTATOES, CUCUMBERS));
    }
}
