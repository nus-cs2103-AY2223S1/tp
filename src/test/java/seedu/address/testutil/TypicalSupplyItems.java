package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Inventory;
import seedu.address.model.item.SupplyItem;

/**
 * A utility class containing a list of {@code SupplyItem} objects to be used in tests.
 */
public class TypicalSupplyItems {
    public static final SupplyItem EGGS = new SupplyItemBuilder().build();

    /**
     * Returns an {@code Inventory} with all the typical supply items.
     */
    public static Inventory getTypicalInventory() {
        Inventory inventory = new Inventory();
        for (SupplyItem supplyItem: getTypicalSupplyItems()) {
            inventory.addSupplyItem(supplyItem);
        }
        return inventory;
    }

    public static List<SupplyItem> getTypicalSupplyItems() {
        return new ArrayList<>(Arrays.asList(EGGS));
    }
}
