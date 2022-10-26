package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.testutil.SortFoodRem.getSortFoodRem;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.itemcomparators.ItemBoughtDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemExpiryDateComparator;
import seedu.foodrem.model.item.itemcomparators.ItemNameComparator;
import seedu.foodrem.model.item.itemcomparators.ItemPriceComparator;
import seedu.foodrem.model.item.itemcomparators.ItemQuantityComparator;
import seedu.foodrem.model.item.itemcomparators.ItemRemarkComparator;
import seedu.foodrem.model.item.itemcomparators.ItemUnitComparator;

class SortCommandTest {
    private final Model model = new ModelManager(getSortFoodRem(), new UserPrefs());

    @Test
    void execute_sortName() {
        SortCommand sortCommandName = new SortCommand(new ItemNameComparator());

        Model tempModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        sortCommandName.execute(tempModel);

        ObservableList<Item> filteredList = tempModel.getCurrentList();
        Item firstItem = filteredList.get(0);
        Item lastItem = filteredList.get(filteredList.size() - 1);
        String firstItemName = String.valueOf(firstItem.getName());
        String lastItemName = String.valueOf(lastItem.getName());
        assertTrue(firstItemName.contains("NA"));
        assertTrue(lastItemName.contains("NL"));
    }

    @Test
    void execute_sortQuantity() {
        SortCommand sortCommandQuantity = new SortCommand(new ItemQuantityComparator());

        Model tempModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        sortCommandQuantity.execute(tempModel);

        ObservableList<Item> sortedItemList = tempModel.getCurrentList();
        int sortedItemListSize = sortedItemList.size();
        for (int i = 1; i <= sortedItemListSize; i += 1) {
            Item item = sortedItemList.get(i - 1);
            String itemName = item.getName().toString();
            assertTrue(itemName.contains("Q" + i));
        }
    }

    @Test
    void execute_sortUnit() {
        SortCommand sortCommandUnit = new SortCommand(new ItemUnitComparator());

        Model tempModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        sortCommandUnit.execute(tempModel);

        ObservableList<Item> sortedItemList = tempModel.getCurrentList();
        int sortedItemListSize = sortedItemList.size();
        for (int i = 1; i <= sortedItemListSize; i += 1) {
            Item item = sortedItemList.get(i - 1);
            String itemName = item.getName().toString();
            assertTrue(itemName.contains("U" + i));
        }
    }

    @Test
    void execute_sortBoughtDate() {
        SortCommand sortCommandBoughtDate = new SortCommand(new ItemBoughtDateComparator());

        Model tempModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        sortCommandBoughtDate.execute(tempModel);

        ObservableList<Item> sortedItemList = tempModel.getCurrentList();
        int sortedItemListSize = sortedItemList.size();
        for (int i = 1; i <= sortedItemListSize; i += 1) {
            Item item = sortedItemList.get(i - 1);
            String itemName = item.getName().toString();
            assertTrue(itemName.contains("B" + i));
        }
    }

    @Test
    void execute_sortExpiryDate() {
        SortCommand sortCommandExpiryDate = new SortCommand(new ItemExpiryDateComparator());

        Model tempModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        sortCommandExpiryDate.execute(tempModel);

        ObservableList<Item> sortedItemList = tempModel.getCurrentList();
        int sortedItemListSize = sortedItemList.size();
        for (int i = 1; i <= sortedItemListSize; i += 1) {
            Item item = sortedItemList.get(i - 1);
            String itemName = item.getName().toString();
            assertTrue(itemName.contains("E" + i));
        }
    }

    @Test
    void execute_sortPrice() {
        SortCommand sortCommandPrice = new SortCommand(new ItemPriceComparator());

        Model tempModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        sortCommandPrice.execute(tempModel);

        ObservableList<Item> sortedItemList = tempModel.getCurrentList();
        int sortedItemListSize = sortedItemList.size();
        for (int i = 1; i <= sortedItemListSize; i += 1) {
            Item item = sortedItemList.get(i - 1);
            String itemName = item.getName().toString();
            assertTrue(itemName.contains("P" + i));
        }
    }

    @Test
    void execute_sortRemarks() {
        SortCommand sortCommandRemarks = new SortCommand(new ItemRemarkComparator());

        Model tempModel = new ModelManager(new FoodRem(model.getFoodRem()), new UserPrefs());
        sortCommandRemarks.execute(tempModel);

        ObservableList<Item> sortedItemList = tempModel.getCurrentList();
        int sortedItemListSize = sortedItemList.size();
        for (int i = 1; i <= sortedItemListSize; i += 1) {
            Item item = sortedItemList.get(i - 1);
            String itemName = item.getName().toString();
            assertTrue(itemName.contains("R" + i));
        }
    }

    @Test
    void testEquals() {
        ItemNameComparator comparator = new ItemNameComparator();
        SortCommand sortCommandSameObjectFirst = new SortCommand(comparator);
        SortCommand sortCommandSameObjectSecond = new SortCommand(comparator);

        SortCommand sortCommandSameFirst = new SortCommand(new ItemNameComparator());
        SortCommand sortCommandSameSecond = new SortCommand(new ItemNameComparator());

        SortCommand sortCommandDifferentFirst = new SortCommand(new ItemQuantityComparator());
        SortCommand sortCommandDifferentSecond = new SortCommand(new ItemPriceComparator());

        // Exactly the same
        assertEquals(sortCommandSameObjectFirst, sortCommandSameObjectSecond);

        // Same comparator different object
        assertEquals(sortCommandSameFirst, sortCommandSameSecond);

        // Different index
        assertNotEquals(sortCommandDifferentFirst, sortCommandDifferentSecond);
    }
}
