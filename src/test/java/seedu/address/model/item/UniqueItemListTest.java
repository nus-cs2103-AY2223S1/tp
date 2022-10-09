package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ITEM_QUANTITY_CUCUMBERS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.CUCUMBERS;
import static seedu.address.testutil.TypicalItems.POTATOES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.testutil.ItemBuilder;

public class UniqueItemListTest {

    private final UniqueItemList uniqueItemList = new UniqueItemList();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueItemList.contains(POTATOES));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueItemList.add(POTATOES);
        assertTrue(uniqueItemList.contains(POTATOES));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItemList.add(POTATOES);
        Item editedAlice = new ItemBuilder(POTATOES).withItemQuantity(VALID_DESC_ITEM_QUANTITY_CUCUMBERS)
            .build();
        assertTrue(uniqueItemList.contains(editedAlice));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItemException() {
        uniqueItemList.add(POTATOES);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.add(POTATOES));
    }

    @Test
    public void setItem_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(null, POTATOES));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(POTATOES, null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.setItem(POTATOES, POTATOES));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        uniqueItemList.add(POTATOES);
        uniqueItemList.setItem(POTATOES, POTATOES);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(POTATOES);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasSameIdentity_success() {
        uniqueItemList.add(POTATOES);
        Item editedAlice = new ItemBuilder(POTATOES).withItemQuantity(VALID_DESC_ITEM_QUANTITY_CUCUMBERS)
            .build();
        uniqueItemList.setItem(POTATOES, editedAlice);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(editedAlice);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasDifferentIdentity_success() {
        uniqueItemList.add(POTATOES);
        uniqueItemList.setItem(POTATOES, CUCUMBERS);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(CUCUMBERS);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueItemList.add(POTATOES);
        uniqueItemList.add(CUCUMBERS);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItem(POTATOES, CUCUMBERS));
    }

    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.remove(POTATOES));
    }

    @Test
    public void remove_existingItem_removesItem() {
        uniqueItemList.add(POTATOES);
        uniqueItemList.remove(POTATOES);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_nullUniqueItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((UniqueItemList) null));
    }

    @Test
    public void setItems_uniqueItemList_replacesOwnListWithProvidedUniqueItemList() {
        uniqueItemList.add(POTATOES);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(CUCUMBERS);
        uniqueItemList.setItems(expectedUniqueItemList);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((List<Item>) null));
    }

    @Test
    public void setItems_list_replacesOwnListWithProvidedList() {
        uniqueItemList.add(POTATOES);
        List<Item> personList = Collections.singletonList(CUCUMBERS);
        uniqueItemList.setItems(personList);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(CUCUMBERS);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItems_listWithDuplicateItems_throwsDuplicateItemException() {
        List<Item> listWithDuplicateItems = Arrays.asList(POTATOES, POTATOES);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItems(listWithDuplicateItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueItemList.asUnmodifiableObservableList().remove(0));
    }
}
