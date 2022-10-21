package tracko.testutil;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import tracko.model.item.Description;
import tracko.model.item.Item;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.tag.Tag;

/**
 * A utility class to help with building Order objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_ITEM_NAME = "Chair";
    public static final String DEFAULT_DESCRIPTION = "This is a wooden dining chair.";

    public static final Integer DEFAULT_QUANTITY = 300;
    public static final Set<Tag> DEFAULT_TAGS = new HashSet<>();

    public static final BigDecimal DEFAULT_SELL_PRICE = new BigDecimal("60");
    public static final BigDecimal DEFAULT_COST_PRICE = new BigDecimal("45");

    private ItemName itemName;
    private Description description;
    private Quantity quantity;
    private Price sellPrice;
    private Price costPrice;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        itemName = new ItemName(DEFAULT_ITEM_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        quantity = new Quantity(DEFAULT_QUANTITY);
        sellPrice = new Price(DEFAULT_SELL_PRICE);
        costPrice = new Price(DEFAULT_COST_PRICE);
        tags = DEFAULT_TAGS;
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        itemName = itemToCopy.getItemName();
        description = itemToCopy.getDescription();
        quantity = itemToCopy.getTotalQuantity();
        sellPrice = itemToCopy.getSellPrice();
        costPrice = itemToCopy.getCostPrice();
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
    public ItemBuilder withQuantity(Integer quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Sell Price} of the {@code Item} that we are building.
     */
    public ItemBuilder withSellPrice(BigDecimal sellPrice) {
        this.sellPrice = new Price(sellPrice);
        return this;
    }

    /**
     * Sets the {@code Cost Price} of the {@code Item} that we are building.
     */
    public ItemBuilder withCostPrice(BigDecimal costPrice) {
        this.costPrice = new Price(costPrice);
        return this;
    }

    /**
     * Sets the {@code Tag}s of the {@code Item} that we are building.
     */
    public ItemBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Builds an item.
     */
    public Item build() {
        return new Item(itemName, description, quantity, new HashSet<>(),
                sellPrice, costPrice);
    }

}
