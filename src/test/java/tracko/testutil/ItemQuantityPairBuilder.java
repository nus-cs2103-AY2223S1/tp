package tracko.testutil;

import tracko.model.order.ItemQuantityPair;

public class ItemQuantityPairBuilder {
    public static final String DEFAULT_ITEM_NAME = "Mechanical Pencil";
    public static final Integer DEFAULT_QUANTITY = 3;

    private String itemName;
    private Integer quantity;

    /**
     * Creates a {@code ItemQuantityPairBuilder} with the default details.
     */
    public ItemQuantityPairBuilder() {
        itemName = DEFAULT_ITEM_NAME;
        quantity = DEFAULT_QUANTITY;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code PairToCopy}.
     */
    public ItemQuantityPairBuilder(ItemQuantityPair PairToCopy) {
        itemName = PairToCopy.getItem();
        quantity = PairToCopy.getQuantity();
    }

    /**
     * Sets the {@code itemName} of the {@code ItemQuantityPair} that we are building.
     */
    public ItemQuantityPairBuilder withItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    /**
     * Sets the {@code quantity} of the {@code ItemQuantityPair} that we are building.
     */
    public ItemQuantityPairBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemQuantityPair build() {
        return new ItemQuantityPair(itemName, quantity);
    }
}
