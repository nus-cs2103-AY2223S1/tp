package tracko.testutil;

import tracko.model.item.Item;
import tracko.model.item.Quantity;
import tracko.model.order.ItemQuantityPair;

/**
 * A utility class to help with building {@code ItemQuantityPair} objects.
 */
public class ItemQuantityPairBuilder {
    public static final Item DEFAULT_ITEM = new ItemBuilder().build();
    public static final Integer DEFAULT_QUANTITY = 3;

    private Item item;
    private Quantity quantity;

    /**
     * Creates a {@code ItemQuantityPairBuilder} with the default details.
     */
    public ItemQuantityPairBuilder() {
        item = DEFAULT_ITEM;
        quantity = new Quantity(DEFAULT_QUANTITY);
    }

    /**
     * Initializes the {@code ItemBuilder} with the data of {@code pairToCopy}.
     */
    public ItemQuantityPairBuilder(ItemQuantityPair pairToCopy) {
        item = pairToCopy.getItem();
        quantity = pairToCopy.getQuantity();
    }

    /**
     * Sets the {@code Item} of the {@code ItemQuantityPair} that we are building.
     */
    public ItemQuantityPairBuilder withItem(Item item) {
        this.item = item;
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
        return new ItemQuantityPair(item, quantity);
    }
}
