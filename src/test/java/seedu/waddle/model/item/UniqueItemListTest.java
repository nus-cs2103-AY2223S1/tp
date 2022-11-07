package seedu.waddle.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SKINNY;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItems.getShopping;
import static seedu.waddle.testutil.TypicalItems.getSkinny;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.waddle.model.item.exceptions.DuplicateItemException;
import seedu.waddle.model.item.exceptions.ItemNotFoundException;
import seedu.waddle.testutil.ItemBuilder;

public class UniqueItemListTest {
    private final UniqueItemList uniqueItemList = new UniqueItemList();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueItemList.contains(getShopping()));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        Item shopping = getShopping();
        uniqueItemList.add(shopping);
        assertTrue(uniqueItemList.contains(shopping));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        Item shopping = getShopping();
        uniqueItemList.add(shopping);
        Item editedShopping = new ItemBuilder(getShopping()).withDuration(VALID_DURATION_SKINNY)
                .withCost(VALID_COST_SKINNY).build();
        assertTrue(uniqueItemList.contains(editedShopping));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItineraryException() {
        Item shopping = getShopping();
        uniqueItemList.add(shopping);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.add(shopping));
    }

    @Test
    public void setItinerary_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(null, getShopping()));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(getShopping(), null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsItemNotFoundException() {
        Item shopping = getShopping();
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.setItem(shopping, shopping));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        Item shopping = getShopping();
        uniqueItemList.add(shopping);
        uniqueItemList.setItem(shopping, shopping);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(shopping);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasSameIdentity_success() {
        Item shopping = getShopping();
        uniqueItemList.add(shopping);
        Item editedShopping = new ItemBuilder(getShopping()).withDuration(VALID_DURATION_SKINNY)
                .withCost(VALID_COST_SKINNY).build();
        uniqueItemList.setItem(shopping, editedShopping);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(editedShopping);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasDifferentIdentity_success() {
        Item shopping = getShopping();
        Item skinny = getSkinny();
        uniqueItemList.add(shopping);
        uniqueItemList.setItem(shopping, skinny);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(skinny);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasNonUniqueIdentity_throwsDuplicateItemException() {
        Item shopping = getShopping();
        Item skinny = getSkinny();
        uniqueItemList.add(shopping);
        uniqueItemList.add(skinny);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItem(shopping, skinny));
    }

    @Test
    public void remove_itemDoesNotExist_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> uniqueItemList.remove(4));
    }

    @Test
    public void remove_existingItem_removesItem() {
        Item shopping = getShopping();
        uniqueItemList.add(shopping);
        uniqueItemList.remove(0);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_nullUniqueItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((UniqueItemList) null));
    }

    @Test
    public void setItem_uniqueItemList_replacesOwnListWithProvidedUniqueItemList() {
        Item shopping = getShopping();
        Item skinny = getSkinny();
        uniqueItemList.add(shopping);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(skinny);
        uniqueItemList.setItems(expectedUniqueItemList);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((List<Item>) null));
    }

    @Test
    public void setItem_list_replacesOwnListWithProvidedList() {
        Item shopping = getShopping();
        Item skinny = getSkinny();
        uniqueItemList.add(shopping);
        List<Item> itemList = Collections.singletonList(skinny);
        uniqueItemList.setItems(itemList);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(skinny);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_listWithDuplicateItem_throwsDuplicateItemException() {
        Item shopping = getShopping();
        List<Item> listWithDuplicateItem = Arrays.asList(shopping, shopping);
        assertThrows(DuplicateItemException.class, ()
                -> uniqueItemList.setItems(listWithDuplicateItem));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueItemList.asUnmodifiableObservableList().remove(0));
    }
}
