package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.SupplyItem;

/**
 * Unmodifiable view of a Inventory
 */
public interface ReadOnlyInventory {

    /**
     * Returns an unmodifiable view of the inventory.
     * This list will not contain any duplicate items.
     */
    ObservableList<SupplyItem> getSupplyItems();
}
