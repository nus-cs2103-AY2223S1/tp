package seedu.foodrem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalItems.POTATOES;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.exceptions.DuplicateItemException;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.ItemBuilder;

public class FoodRemTest {

    private final FoodRem foodRem = new FoodRem();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), foodRem.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodRem.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFoodRem_replacesData() {
        FoodRem newData = getTypicalFoodRem();
        foodRem.resetData(newData);
        assertEquals(newData, foodRem);
    }

    @Test
    public void resetData_withDuplicateItems_throwsDuplicateItemException() {
        // Two items with the same identity fields
        Item editedPotatoes = new ItemBuilder(POTATOES)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
                .build();
        List<Item> newItems = Arrays.asList(POTATOES, editedPotatoes);
        FoodRemStub newData = new FoodRemStub(newItems);

        assertThrows(DuplicateItemException.class, () -> foodRem.resetData(newData));
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodRem.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInFoodRem_returnsFalse() {
        assertFalse(foodRem.hasItem(POTATOES));
    }

    @Test
    public void hasItem_itemInFoodRem_returnsTrue() {
        foodRem.addItem(POTATOES);
        assertTrue(foodRem.hasItem(POTATOES));
    }

    @Test
    public void hasItem_itemWithSameIdentityFieldsInFoodRem_returnsTrue() {
        foodRem.addItem(POTATOES);
        Item editedPotatoes = new ItemBuilder(POTATOES)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
                .build();
        assertTrue(foodRem.hasItem(editedPotatoes));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> foodRem.getItemList().remove(0));
    }

    /**
     * A stub ReadOnlyFoodRem whose items list can violate interface constraints.
     */
    private static class FoodRemStub implements ReadOnlyFoodRem {
        private final ObservableList<Item> items = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        FoodRemStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
