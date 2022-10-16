package tracko.testutil;

import java.math.BigDecimal;
import java.util.HashSet;

import tracko.model.items.Description;
import tracko.model.items.Item;
import tracko.model.items.ItemName;
import tracko.model.items.Price;
import tracko.model.items.Quantity;

/**
 * A utility class to help with building Order objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_ITEM_NAME = "Chair";
    public static final String DEFAULT_DESCRIPTION = "This is a wooden dining chair.";
    public static final int DEFAULT_QUANTITY = 300;

    private ItemName itemName;
    private Description description;
    private Quantity quantity;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        itemName = new ItemName(DEFAULT_ITEM_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        quantity = new Quantity(DEFAULT_QUANTITY);
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        itemName = itemToCopy.getItemName();
        description = itemToCopy.getDescription();
        quantity = itemToCopy.getQuantity();
    }

    /**
     * Sets the {@code ItemName} of the {@code Item} that we are building.
     */
    public ItemBuilder withItemName(String name) {
        this.itemName = new ItemName(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Item} that we are building.
     */
    public ItemBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Item} that we are building.
     */
    public ItemBuilder withQuantity(int quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Builds an item.
     */
    public Item build() {
        return new Item(itemName, description, quantity, new HashSet<>(),
                new Price(new BigDecimal("0")), new Price(new BigDecimal("0")));
    }

}
