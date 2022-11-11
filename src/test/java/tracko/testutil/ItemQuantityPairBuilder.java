package tracko.testutil;

import tracko.model.item.InventoryItem;
import tracko.model.item.Quantity;
import tracko.model.order.ItemQuantityPair;

/**
 * A utility class to help with building {@code ItemQuantityPair} objects.
 */
public class ItemQuantityPairBuilder {
    public static final InventoryItem DEFAULT_INVENTORY_ITEM = new InventoryItemBuilder().build();
    public static final Integer DEFAULT_QUANTITY = 3;

    private InventoryItem inventoryItem;
    private Quantity quantity;

    /**
     * Creates a {@code ItemQuantityPairBuilder} with the default details.
     */
    public ItemQuantityPairBuilder() {
        inventoryItem = DEFAULT_INVENTORY_ITEM;
        quantity = new Quantity(DEFAULT_QUANTITY);
    }

    // /**
    //  * Initializes the {@code ItemBuilder} with the data of {@code pairToCopy}.
    //  */
    // public ItemQuantityPairBuilder(ItemQuantityPair pairToCopy) {
    //     inventoryItem = pairToCopy.getItem();
    //     quantity = pairToCopy.getQuantity();
    // }

    /**
     * Sets the {@code Item} of the {@code ItemQuantityPair} that we are building.
     */
    public ItemQuantityPairBuilder withItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code ItemQuantityPair} that we are building.
     */
    public ItemQuantityPairBuilder withQuantity(Integer quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    public ItemQuantityPair build() {
        return new ItemQuantityPair(inventoryItem, quantity);
    }
}
