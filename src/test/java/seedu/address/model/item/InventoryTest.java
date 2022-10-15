package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSupplyItems.EGGS;
import static seedu.address.testutil.TypicalSupplyItems.GINGER;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Inventory;


public class InventoryTest {

    private final Inventory inventory = new Inventory();
    private final Inventory typicalInventory = getTypicalInventory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), inventory.getSupplyItems());
    }

    @Test
    public void constructor_copyInventory_equalToOriginal() {
        assertEquals(typicalInventory, new Inventory(typicalInventory));
    }

    @Test
    public void setSupplyItems_withValidListOfSupplyItem_equalToOriginal() {
        inventory.setSupplyItems(typicalInventory.getSupplyItems());
        assertEquals(typicalInventory, inventory);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInventory_replacesData() {
        Inventory newData = getTypicalInventory();
        inventory.resetData(newData);
        assertEquals(newData, inventory);
    }

    @Test
    public void addItem_nullSupplyItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.addSupplyItem(null));
    }

    @Test
    public void hasItem_nullSupplyItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.hasSupplyItem(null));
    }

    @Test
    public void hasSupplyItem_itemNotInInventory_returnsFalse() {
        assertFalse(inventory.hasSupplyItem(EGGS));
    }

    @Test
    public void hasSupplyItem_itemInInventory_returnsTrue() {
        inventory.addSupplyItem(EGGS);
        assertTrue(inventory.hasSupplyItem(EGGS));
    }

    @Test
    public void setSupplyItem_nullSupplyItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.setSupplyItem(null, Index.fromZeroBased(0)));
    }

    @Test
    public void setSupplyItem_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.setSupplyItem(EGGS, null));
    }

    @Test
    public void setSupplyItem_indexOutOfBound_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> inventory.setSupplyItem(EGGS, Index.fromZeroBased(0)));
    }

    @Test
    public void setSupplyItem_validSupplyItemAndIndex_replacesSupplyItem() {
        inventory.addSupplyItem(EGGS);
        inventory.setSupplyItem(GINGER, Index.fromZeroBased(0));
        assertEquals(GINGER, inventory.getSupplyItems().get(0));
    }

    @Test
    public void deleteSupplyItem_indexOutOfBound_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> inventory.deleteSupplyItem(Index.fromZeroBased(0)));
    }

    @Test
    public void deleteSupplyItem_validIndex_success() {
        inventory.addSupplyItem(EGGS);
        inventory.deleteSupplyItem(Index.fromZeroBased(0));
        assertEquals(Collections.emptyList(), inventory.getSupplyItems());
    }

    @Test
    public void getInventory_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> typicalInventory.getSupplyItems().remove(0));
    }
}
