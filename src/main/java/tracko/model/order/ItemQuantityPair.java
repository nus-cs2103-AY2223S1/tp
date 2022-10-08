package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import javafx.util.Pair;

/**
 * Represents an item and quantity pair to be added to an {@code Order's} item list.
 * */
public class ItemQuantityPair extends Pair<String, Integer> {

    /**
     * Constructs an ItemQuantityPair with the given item and quantity.
     * @param item The given item
     * @param quantity The given quantity
     */
    public ItemQuantityPair(String item, Integer quantity) {
        super(item, quantity);
        requireAllNonNull(item, quantity);
    }

    public String getItem() {
        return this.getKey();
    }

    public Integer getQuantity() {
        return this.getValue();
    }

    @Override
    public String toString() {
        return this.getQuantity() + " * " + this.getItem();
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
