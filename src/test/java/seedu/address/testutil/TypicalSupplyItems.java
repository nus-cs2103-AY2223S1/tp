package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.BENSON_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.CARL_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.DANIEL_SUPPLIER;

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
    public static final SupplyItem GINGER = new SupplyItemBuilder().withSupplierPerson(ALICE_SUPPLIER)
            .withCurrentStock(99).withMinStock(1).withItemName("Ginger").build();
    public static final SupplyItem BEEF = new SupplyItemBuilder().withSupplierPerson(BENSON_SUPPLIER)
            .withCurrentStock(99).withMinStock(1).withItemName("Beef").build();
    public static final SupplyItem LAMB = new SupplyItemBuilder().withSupplierPerson(CARL_SUPPLIER)
            .withCurrentStock(99).withMinStock(1).withItemName("Lamb").build();
    public static final SupplyItem SPINACH = new SupplyItemBuilder().withSupplierPerson(DANIEL_SUPPLIER)
            .withCurrentStock(99).withMinStock(1).withItemName("Spinach").build();
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
        return new ArrayList<>(Arrays.asList(EGGS, GINGER, BEEF));
    }
}
