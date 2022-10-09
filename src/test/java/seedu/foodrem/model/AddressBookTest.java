package seedu.foodrem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_BOUGHT_DATE_POTATOES;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalItems.POTATOES;
import static seedu.foodrem.testutil.TypicalItems.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.exceptions.DuplicateItemException;
import seedu.foodrem.testutil.ItemBuilder;

public class AddressBookTest {

    private final FoodRem addressBook = new FoodRem();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FoodRem newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateItems_throwsDuplicateItemException() {
        // Two persons with the same identity fields
        Item editedPotatoes = new ItemBuilder(POTATOES)
            .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
            .build();
        List<Item> newItems = Arrays.asList(POTATOES, editedPotatoes);
        FoodRemStub newData = new FoodRemStub(newItems);

        assertThrows(DuplicateItemException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasItem(null));
    }

    @Test
    public void hasItem_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasItem(POTATOES));
    }

    @Test
    public void hasItem_personInAddressBook_returnsTrue() {
        addressBook.addItem(POTATOES);
        assertTrue(addressBook.hasItem(POTATOES));
    }

    @Test
    public void hasItem_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addItem(POTATOES);
        Item editedPotatoes = new ItemBuilder(POTATOES)
            .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_POTATOES)
            .build();
        assertTrue(addressBook.hasItem(editedPotatoes));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getItemList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class FoodRemStub implements ReadOnlyFoodRem {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        FoodRemStub(Collection<Item> persons) {
            this.items.setAll(persons);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }
    }

}
