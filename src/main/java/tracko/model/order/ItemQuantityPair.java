package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import tracko.model.item.Item;
import tracko.model.item.Quantity;

/**
 * Represents an item and quantity pair to be added to an {@code Order's} item list.
 * */
public class ItemQuantityPair {

    private final Item item;
    private final Quantity quantity;

    /**
     *  * Constructs an ItemQuantityPair with the given Item and Quantity.
     * @param item The given item
     * @param quantity The given quantity
     */
    public ItemQuantityPair(Item item, Quantity quantity) {
        requireAllNonNull(item, quantity);
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Returns the referenced {@code Item} object of this ItemQuantityPair.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the item name of the referenced {@code Item} object of this ItemQuantityPair.
     */
    public String getItemName() {
        return getItem().getItemName().toString();
    }

    /**
     * Get the referenced {@code Quantity} object of this ItemQuantityPair.
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Returns the quantity value of the referenced {@code Quantity} object of this ItemQuantityPair.
     */
    public Integer getQuantityValue() {
        return getQuantity().getQuantity();
    }

    @Override
    public String toString() {
        return this.getQuantity() + " * " + this.getItemName();
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
