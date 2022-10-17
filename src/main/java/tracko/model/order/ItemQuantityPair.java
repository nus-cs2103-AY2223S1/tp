package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import javafx.util.Pair;
import tracko.model.item.Item;
import tracko.model.item.Quantity;

/**
 * Represents an item and quantity pair to be added to an {@code Order's} item list.
 * */
public class ItemQuantityPair extends Pair<Item, Quantity> {

    /**
     * Constructs an ItemQuantityPair with the given Item and Quantity.
     * @param item The given item
     * @param quantity The given quantity
     */
    public ItemQuantityPair(Item item, Quantity quantity) {
        super(item, quantity);
        requireAllNonNull(item, quantity);
    }

    public Item getItem() {
        return this.getKey();
    }

    public String getItemName() {
        return getItem().getItemName().toString();
    }

    public Quantity getQuantity() {
        return this.getValue();
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
