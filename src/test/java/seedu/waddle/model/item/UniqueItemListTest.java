package seedu.waddle.model.item;

import seedu.waddle.model.item.exceptions.DuplicateItemException;
import seedu.waddle.model.item.exceptions.ItemNotFoundException;
import seedu.waddle.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.waddle.testutil.ItemBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SKINNY;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItems.SHOPPING;
import static seedu.waddle.testutil.TypicalItems.SKINNY;

public class UniqueItemListTest {
    private final UniqueItemList uniqueItemList = new UniqueItemList();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueItemList.contains(SHOPPING));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueItemList.add(SHOPPING);
        assertTrue(uniqueItemList.contains(SHOPPING));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItemList.add(SHOPPING);
        Item editedShopping = new ItemBuilder(SHOPPING).withDuration(VALID_DURATION_SKINNY)
                .withCost(VALID_COST_SKINNY).build();
        assertTrue(uniqueItemList.contains(editedShopping));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItineraryException() {
        uniqueItemList.add(SHOPPING);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.add(SHOPPING));
    }

    @Test
    public void setItinerary_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(null, SHOPPING));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItem(SHOPPING, null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueItemList.setItem(SHOPPING, SHOPPING));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        uniqueItemList.add(SHOPPING);
        uniqueItemList.setItem(SHOPPING, SHOPPING);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(SHOPPING);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasSameIdentity_success() {
        uniqueItemList.add(SHOPPING);
        Item editedShopping = new ItemBuilder(SHOPPING).withDuration(VALID_DURATION_SKINNY)
                .withCost(VALID_COST_SKINNY).build();
        uniqueItemList.setItem(SHOPPING, editedShopping);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(editedShopping);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasDifferentIdentity_success() {
        uniqueItemList.add(SHOPPING);
        uniqueItemList.setItem(SHOPPING, SKINNY);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(SKINNY);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_editedItemHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueItemList.add(SHOPPING);
        uniqueItemList.add(SKINNY);
        assertThrows(DuplicateItemException.class, () -> uniqueItemList.setItem(SHOPPING, SKINNY));
    }

    @Test
    public void remove_itemDoesNotExist_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> uniqueItemList.remove(4));
    }

    @Test
    public void remove_existingItem_removesItem() {
        uniqueItemList.add(SHOPPING);
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
        uniqueItemList.add(SHOPPING);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(SKINNY);
        uniqueItemList.setItems(expectedUniqueItemList);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItemList.setItems((List<Item>) null));
    }

    @Test
    public void setItem_list_replacesOwnListWithProvidedList() {
        uniqueItemList.add(SHOPPING);
        List<Item> itemList = Collections.singletonList(SKINNY);
        uniqueItemList.setItems(itemList);
        UniqueItemList expectedUniqueItemList = new UniqueItemList();
        expectedUniqueItemList.add(SKINNY);
        assertEquals(expectedUniqueItemList, uniqueItemList);
    }

    @Test
    public void setItem_listWithDuplicateItem_throwsDuplicateItemException() {
        List<Item> listWithDuplicateItem = Arrays.asList(SHOPPING, SHOPPING);
        assertThrows(DuplicateItemException.class, ()
                -> uniqueItemList.setItems(listWithDuplicateItem));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueItemList.asUnmodifiableObservableList().remove(0));
    }
}
