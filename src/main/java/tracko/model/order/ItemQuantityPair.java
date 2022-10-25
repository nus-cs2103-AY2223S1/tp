package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import tracko.model.item.InventoryItem;
import tracko.model.item.Quantity;

/**
 * Represents an item and quantity pair to be added to an {@code Order's} item list.
 * */
public class ItemQuantityPair {

    private final InventoryItem inventoryItem;
    private Quantity quantity;

    /**
     *  * Constructs an ItemQuantityPair with the given Item and Quantity.
     * @param inventoryItem The given item
     * @param quantity The given quantity
     */
    public ItemQuantityPair(InventoryItem inventoryItem, Quantity quantity) {
        requireAllNonNull(inventoryItem, quantity);
        this.inventoryItem = inventoryItem;
        this.quantity = quantity;
    }

    /**
     * Returns the referenced {@code Item} object of this ItemQuantityPair.
     */
    public InventoryItem getItem() {
        return inventoryItem;
    }

    /**
     * Returns the item name of the referenced {@code Item} object of this ItemQuantityPair.
     */
    public String getItemName() {
        return inventoryItem.getItemName().toString();
    }

    /**
     * Get the referenced {@code Quantity} object of this ItemQuantityPair.
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Sets the {@code Quantity} of this ItemQuantityPair.
     */
    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns true if the given {@code ItemQuantityPair} has the same item.
     */
    public boolean hasSameItem(ItemQuantityPair otherPair) {
        return inventoryItem.isSameItem(otherPair.inventoryItem);
    }

    /**
     * Returns the quantity value of the referenced {@code Quantity} object of this ItemQuantityPair.
     */
    public Integer getQuantityValue() {
        return quantity.getQuantity();
    }

    @Override
    public String toString() {
        return quantity + " * " + this.getItemName();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItemQuantityPair)) {
            return false;
        }

        ItemQuantityPair otherPair = (ItemQuantityPair) other;
        return getItem().equals(otherPair.getItem())
            && getQuantity().equals(otherPair.getQuantity());
    }
}
