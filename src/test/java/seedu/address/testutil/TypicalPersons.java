package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Food;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Food ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTags("friends").build();
    public static final Food BENSON = new PersonBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Food CARL = new PersonBuilder().withName("Carl Kurz").build();
    public static final Food DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withTags("friends").build();
    public static final Food ELLE = new PersonBuilder().withName("Elle Meyer").build();
    public static final Food FIONA = new PersonBuilder().withName("Fiona Kunz").build();
    public static final Food GEORGE = new PersonBuilder().withName("George Best").build();

    // Manually added
    public static final Food HOON = new PersonBuilder().withName("Hoon Meier").build();
    public static final Food IDA = new PersonBuilder().withName("Ida Mueller").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Food AMY = new PersonBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Food BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Food food : getTypicalPersons()) {
            ab.addPerson(food);
        }
        return ab;
    }

    public static List<Food> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
