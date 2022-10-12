package seedu.foodrem.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.foodrem.model.item.Item;

/**
 * A utility class containing a list of {@code item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item POTATOES = new ItemBuilder()
            .withItemName("Potatoes")
            .withItemQuantity("10")
            .withItemUnit("kg")
            .withItemBoughtDate("11-11-2022")
            .withItemExpiryDate("11-11-2022").build();

    public static final Item CUCUMBERS = new ItemBuilder()
            .withItemName("Cucumbers")
            .withItemQuantity("2000")
            .withItemUnit("grams")
            .withItemBoughtDate("12-12-2022")
            .withItemExpiryDate("12-12-2022").build();

    public static final Item CARROTS = new ItemBuilder()
            .withItemName("Carrots")
            .build();

    private TypicalItems() {
    } // prevents instantiation

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(POTATOES, CUCUMBERS));
    }
}
