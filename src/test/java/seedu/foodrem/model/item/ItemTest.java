package seedu.foodrem.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_EXPIRY_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_REMARKS_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodrem.testutil.ItemBuilder;

class ItemTest {
    private static final List<Item> initialItems = new ArrayList<>();
    private List<Item> itemsCopy;

    /**
     * Populates a list with subsets of items of size 0, 1, 2 and full.
     * @param itemList the list of items to populate with the generated subsets.
     */
    private static void generateFieldCombinations(List<Item> itemList) {
        // 0 defined attributes
        itemList.add(new ItemBuilder().build());

        // 1 defined attribute
        itemList.add(new ItemBuilder().withItemName(VALID_ITEM_NAME_CUCUMBERS).build());
        itemList.add(new ItemBuilder().withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS).build());
        itemList.add(new ItemBuilder().withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS).build());
        itemList.add(new ItemBuilder().withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS).build());
        itemList.add(new ItemBuilder().withItemPrice(VALID_ITEM_PRICE_CUCUMBERS).build());
        itemList.add(new ItemBuilder().withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS).build());
        itemList.add(new ItemBuilder().withTags(VALID_TAG_NAME_VEGETABLES).build());

        // 2 defined attributes
        itemList.add(new ItemBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withTags(VALID_TAG_NAME_VEGETABLES)
                .build());

        itemList.add(new ItemBuilder()
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .withTags(VALID_TAG_NAME_VEGETABLES)
                .build());

        itemList.add(new ItemBuilder()
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .withTags(VALID_TAG_NAME_VEGETABLES)
                .build());

        itemList.add(new ItemBuilder()
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .withTags(VALID_TAG_NAME_VEGETABLES)
                .build());

        itemList.add(new ItemBuilder()
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .build());
        itemList.add(new ItemBuilder()
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .withTags(VALID_TAG_NAME_VEGETABLES)
                .build());

        itemList.add(new ItemBuilder()
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .withTags(VALID_TAG_NAME_VEGETABLES)
                .build());

        // all 7 attributes defined. TODO: remember to update each time a new field is added
        itemList.add(new ItemBuilder()
                .withItemName(VALID_ITEM_NAME_CUCUMBERS)
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS)
                .withItemPrice(VALID_ITEM_PRICE_CUCUMBERS)
                .withItemRemarks(VALID_ITEM_REMARKS_CUCUMBERS)
                .withTags(VALID_TAG_NAME_VEGETABLES)
                .build());
    }

    @BeforeAll
    public static void setInitialItems() {
        generateFieldCombinations(initialItems);
    }

    @BeforeEach
    private void copyItems() {
        itemsCopy = new ArrayList<>();
        generateFieldCombinations(itemsCopy);
        assert itemsCopy.size() == initialItems.size();
    }

    @Test
    void testEquals() {
        for (int i = 0; i < initialItems.size(); i++) {
            assertEquals(initialItems.get(i), itemsCopy.get(i));
        }
    }

    @Test
    void testHashCode() {
        for (int i = 0; i < initialItems.size(); i++) {
            assertEquals(initialItems.get(i).hashCode(), itemsCopy.get(i).hashCode());
        }
    }
}
