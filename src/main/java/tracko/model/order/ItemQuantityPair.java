package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import javafx.util.Pair;

/**
 * Represents an item and quantity pair to be added to an {@code Order's} item list.
 * */
public class ItemQuantityPair extends Pair<String, Integer> {

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
}
