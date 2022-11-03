package tracko.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.tag.Tag;

/**
 * A utility class to help with building {@InventoryItem} objects.
 */
public class InventoryItemBuilder {

    public static final String DEFAULT_ITEM_NAME = "Chair";
    public static final String DEFAULT_DESCRIPTION = "This is a wooden dining chair.";

    public static final Integer DEFAULT_QUANTITY = 300;
    public static final Set<Tag> DEFAULT_TAGS = new HashSet<>();

    public static final Double DEFAULT_SELL_PRICE = 60.00;
    public static final Double DEFAULT_COST_PRICE = 45.00;

    private ItemName itemName;
    private Description description;
    private Quantity quantity;
    private Price sellPrice;
    private Price costPrice;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public InventoryItemBuilder() {
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
    public InventoryItemBuilder(InventoryItem inventoryItemToCopy) {
        itemName = inventoryItemToCopy.getItemName();
        description = inventoryItemToCopy.getDescription();
        quantity = inventoryItemToCopy.getTotalQuantity();
        sellPrice = inventoryItemToCopy.getSellPrice();
        costPrice = inventoryItemToCopy.getCostPrice();
    }

    /**
     * Sets the {@code ItemName} of the {@code Item} that we are building.
     */
    public InventoryItemBuilder withItemName(String name) {
        this.itemName = new ItemName(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Item} that we are building.
     */
    public InventoryItemBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Item} that we are building.
     */
    public InventoryItemBuilder withQuantity(Integer quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Sell Price} of the {@code Item} that we are building.
     */
    public InventoryItemBuilder withSellPrice(Double sellPrice) {
        this.sellPrice = new Price(sellPrice);
        this.sellPrice.getValue();
        return this;
    }

    /**
     * Sets the {@code Cost Price} of the {@code Item} that we are building.
     */
    public InventoryItemBuilder withCostPrice(Double costPrice) {
        this.costPrice = new Price(costPrice);
        return this;
    }

    /**
     * Sets the {@code Tag}s of the {@code Item} that we are building.
     */
    public InventoryItemBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        this.tags = tagSet;
        return this;
    }

    /**
     * Builds an item.
     */
    public InventoryItem build() {
        return new InventoryItem(itemName, description, quantity, new HashSet<>(),
                sellPrice, costPrice);
    }

}
