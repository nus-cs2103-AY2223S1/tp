package seedu.foodrem.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.model.item.Item;

/**
 * A utility class containing a list of {@code item} objects to be used in tests.
 */
public class TypicalItems {
    public static final Item POTATOES = new ItemBuilder()
            .withItemName("Potatoes")
            .withItemQuantity("10")
            .withItemUnit("kg")
            .withItemBoughtDate("11-10-2022")
            .withItemExpiryDate("11-11-2022")
            .withItemPrice("10.30")
            .withItemRemarks("For Mashed Potatoes")
            .withTags(CommandTestUtil.VALID_TAG_NAME_VEGETABLES).build();

    public static final Item CUCUMBERS = new ItemBuilder()
            .withItemName("Cucumbers")
            .withItemQuantity("2000")
            .withItemUnit("grams")
            .withItemBoughtDate("12-10-2022")
            .withItemExpiryDate("12-12-2022")
            .withItemPrice("8")
            .withItemRemarks("For Salad")
            .withTags(CommandTestUtil.VALID_TAG_NAME_VEGETABLES).build();

    public static final Item CARROTS = new ItemBuilder()
            .withItemName("Carrots")
            .withTags(CommandTestUtil.VALID_TAG_NAME_VEGETABLES).build();

    public static final Item POTATOES_WITHOUT_TAG = new ItemBuilder()
            .withItemName("Potatoes")
            .withItemQuantity("10")
            .withItemUnit("kg")
            .withItemBoughtDate("11-10-2022")
            .withItemExpiryDate("11-11-2022")
            .withItemPrice("10.30")
            .withItemRemarks("For Mashed Potatoes")
            .build();

    public static final Item CUCUMBERS_WITHOUT_TAG = new ItemBuilder()
            .withItemName("Cucumbers")
            .withItemQuantity("2000")
            .withItemUnit("grams")
            .withItemBoughtDate("12-10-2022")
            .withItemExpiryDate("12-12-2022")
            .withItemPrice("8")
            .build();

    private TypicalItems() {
    } // prevents instantiation

    /**
     * Returns a list of typical items.
     */
    public static List<Item> getTypicalItems() {
        // WARNING: Ensure all items have a vegetable tag.
        // Failure to do so will break test cases.
        return new ArrayList<>(Arrays.asList(POTATOES, CUCUMBERS));
    }

    /**
     * Returns a list of typical items without tags.
     */
    public static List<Item> getTypicalItemsWithoutTags() {
        return new ArrayList<>(Arrays.asList(POTATOES_WITHOUT_TAG, CUCUMBERS_WITHOUT_TAG));
    }
}
