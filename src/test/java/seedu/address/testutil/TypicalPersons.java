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

    public static final Food APPLE = new FoodBuilder().withName("Apple")
            .withTags("breakfast").build();
    public static final Food BREAD = new FoodBuilder().withName("Bread")
            .withTags("lunch").build();
    public static final Food CARBONARA = new FoodBuilder().withName("Carbonara")
            .withTags("dinner").build();
    public static final Food DRAGON_FRUIT = new FoodBuilder().withName("Dragon fruit").build();
    public static final Food EGG_SOUP = new FoodBuilder().withName("Egg soup").build();
    public static final Food FISHCAKE = new FoodBuilder().withName("Fishcake").build();
    public static final Food GRAPES = new FoodBuilder().withName("Grapes").build();

    // Manually added
    public static final Food HOON = new FoodBuilder().withName("Hoon Meier").build();
    public static final Food IDA = new FoodBuilder().withName("Ida Mueller").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Food AMY = new FoodBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Food BOB = new FoodBuilder().withName(VALID_NAME_BOB)
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
        return new ArrayList<>(Arrays.asList(APPLE, BREAD, CARBONARA, DRAGON_FRUIT, EGG_SOUP, FISHCAKE, GRAPES));
    }
}
