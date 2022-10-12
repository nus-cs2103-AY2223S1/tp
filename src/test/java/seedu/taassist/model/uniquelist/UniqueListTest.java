package seedu.taassist.model.uniquelist;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.model.uniquelist.exceptions.DuplicateElementException;
import seedu.taassist.model.uniquelist.exceptions.ElementNotFoundException;

class UniqueListTest {

    private static final Item DUMMY1 = new Item("dummy 1", "dummy data 1");
    private static final Item DUMMY2 = new Item("dummy 2", "dummy data 2");

    private final UniqueList<Item> uniqueList = new UniqueList<>();

    @Test
    public void contains_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueList.contains(DUMMY1));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueList.add(DUMMY1);
        assertTrue(uniqueList.contains(DUMMY1));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueList.add(DUMMY1);
        Item editedDummyItem = new Item(DUMMY1.identity);
        assertTrue(uniqueList.contains(editedDummyItem));
    }

    @Test
    public void add_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.add(null));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItemException() {
        uniqueList.add(DUMMY1);
        assertThrows(DuplicateElementException.class, () -> uniqueList.add(DUMMY1));
    }

    @Test
    public void setItem_nullTargetItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setElement(null, DUMMY1));
    }

    @Test
    public void setItem_nullEditedItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setElement(DUMMY1, null));
    }

    @Test
    public void setItem_targetItemNotInList_throwsElementNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueList.setElement(DUMMY1, DUMMY1));
    }

    @Test
    public void setItem_editedItemIsSameItem_success() {
        uniqueList.add(DUMMY1);
        uniqueList.setElement(DUMMY1, DUMMY1);
        UniqueList<Item> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(DUMMY1);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setItem_editedItemExists_throwsDuplicateItemException() {
        uniqueList.add(DUMMY1);
        uniqueList.add(DUMMY2);
        assertThrows(DuplicateElementException.class, () -> uniqueList.setElement(DUMMY1, DUMMY2));
    }


    @Test
    public void remove_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueList.remove(DUMMY1));
    }

    @Test
    public void remove_existingItem_removesItem() {
        uniqueList.add(DUMMY1);
        uniqueList.remove(DUMMY1);
        UniqueList<Item> expectedUniqueList = new UniqueList<>();
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setElements_nullUniqueList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setElements((UniqueList<Item>) null));
    }

    @Test
    public void setElements_uniqueList_replacesOwnListWithProvidedUniqueList() {
        uniqueList.add(DUMMY1);
        UniqueList<Item> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(DUMMY2);
        uniqueList.setElements(expectedUniqueList);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setElements_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.setElements((List<Item>) null));
    }

    @Test
    public void setElements_list_replacesOwnListWithProvidedList() {
        uniqueList.add(DUMMY1);
        List<Item> itemList = List.of(DUMMY2);
        uniqueList.setElements(itemList);
        UniqueList<Item> expectedUniqueList = new UniqueList<>();
        expectedUniqueList.add(DUMMY2);
        assertEquals(expectedUniqueList, uniqueList);
    }

    @Test
    public void setElements_listWithDuplicateItems_throwsDuplicateItemException() {
        List<Item> listWithDuplicateItems = List.of(DUMMY1, DUMMY1);
        assertThrows(DuplicateElementException.class, () -> uniqueList.setElements(listWithDuplicateItems));
    }

    @Test
    public void findElement_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueList.findElement(null));
    }

    @Test
    public void findElement_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueList.findElement(DUMMY1));
    }

    @Test
    public void findElement_existingItem_returnsItem() {
        uniqueList.add(DUMMY1);
        assertEquals(DUMMY1, uniqueList.findElement(DUMMY1));
    }

    @Test
    public void findElement_itemWithSameIdentityFieldsInList_returnsItem() {
        uniqueList.add(DUMMY1);
        Item editedDummyItem = new Item(DUMMY1.identity);
        assertEquals(DUMMY1, uniqueList.findElement(editedDummyItem));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueList.asUnmodifiableObservableList().remove(0));
    }

    /**
     * A class that has a String identity as well as a data field.
     */
    private static class Item implements Identity<Item> {

        private String identity;
        private String data;

        public Item(String identity) {
            requireNonNull(identity);
            this.identity = identity;
        }

        public Item(String identity, String data) {
            requireAllNonNull(identity, data);
            this.identity = identity;
            this.data = data;
        }

        @Override
        public boolean isSame(Item other) {
            if (other == this) {
                return true;
            }
            return other != null && other.identity.equals(identity);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof Item // instanceof handles nulls
                    && identity.equals(((Item) other).identity))
                    && data.equals(((Item) other).data); // state check
        }
    }
}
