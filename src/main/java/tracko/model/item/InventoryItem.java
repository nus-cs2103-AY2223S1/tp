package tracko.model.item;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import tracko.model.tag.Tag;

/**
 * Represents an item in the inventory list.
 * Guarantees: details are present and not null, field values are validated.
 */
public class InventoryItem implements Item {
    private ItemName itemName;
    private Description description;
    private Quantity totalQuantity;
    private Price sellPrice;
    private Price costPrice;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Constructs an {@code item}.
     * @param itemName The name of the item
     * @param description The description of the item
     * @param quantity The quantity of the item
     * @param tags The tags associated with the item
     * @param sellPrice The price the item is sold at
     * @param costPrice The price the item cost to produce
     */
    public InventoryItem(ItemName itemName, Description description, Quantity quantity, Set<Tag> tags,
                         Price sellPrice, Price costPrice) {
        requireAllNonNull(itemName, description, tags, sellPrice, costPrice);
        this.itemName = itemName;
        this.description = description;
        this.totalQuantity = quantity;
        this.tags.addAll(tags);
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
    }

    @Override
    public ItemName getItemName() {
        return itemName;
    }

    @Override
    public Price getSellPrice() {
        return sellPrice;
    }

    @Override
    public Price getCostPrice() {
        return costPrice;
    }

    public Description getDescription() {
        return description;
    }

    public Quantity getTotalQuantity() {
        return totalQuantity;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if the invoked {@code InventoryItem} has equal to or more than input quantity
     * @param quantity The input quantity
     * @return True if the invoked {@code InventoryItem} has equal to or more than input quantity.
     */
    public boolean hasMoreThan(Quantity quantity) {
        return totalQuantity.value >= quantity.value;
    }

    /**
     * Decreases the stock quantity of the item by the delivered amount.
     * @param deliveredQuantity quantity of the item that has been delivered
     */
    public void reduceItem(Integer deliveredQuantity) {
        Quantity newQuantity = new Quantity(this.totalQuantity.value - deliveredQuantity);
        this.totalQuantity = newQuantity;
    }

    /**
     * Updates the invoked {@code Item} reference with the given {@code Item}'s data.
     */
    public void updateData(InventoryItem toCopy) {
        this.itemName = toCopy.itemName;
        this.description = toCopy.description;
        this.totalQuantity = toCopy.totalQuantity;
        this.tags = toCopy.tags;
        this.sellPrice = toCopy.sellPrice;
        this.costPrice = toCopy.costPrice;
    }

    /**
     * Returns true if given item name is the same name.
     */
    public boolean nameMatches(String itemName) {
        return itemName.equalsIgnoreCase(this.itemName.toString());
    }

    /**
     * Returns true if both items are {@code InventoryItem}s and have the same name.
     * This defines a weaker notion of equality between two items.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return (otherItem instanceof InventoryItem)
            && otherItem.getItemName().equals(getItemName());
    }

    /**
     * Returns a {@code RecordedItem} object that represents a copy of this {@code InventoryItem} at the point of
     * invocation.
     * @return A {@code RecordedItem} object that represents a copy of this {@code InventoryItem}.
     */
    @Override
    public RecordedItem getRecordedItem() {
        return RecordedItem.getRecordedItemCopy(this);
    }

    /**
     * Returns true if both items have the same identity and data fields.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InventoryItem)) {
            return false;
        }

        InventoryItem otherInventoryItem = (InventoryItem) other;
        return otherInventoryItem.getItemName().equals(getItemName())
                && otherInventoryItem.getDescription().equals(getDescription())
                && otherInventoryItem.getTotalQuantity().equals(getTotalQuantity())
                && otherInventoryItem.getTags().equals(getTags())
                && otherInventoryItem.getCostPrice().equals(getCostPrice())
                && otherInventoryItem.getSellPrice().equals(getSellPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName, description, totalQuantity, tags, sellPrice, costPrice);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Name: ")
                .append(getItemName() + "\n")
                .append("Description: ")
                .append(getDescription() + "\n")
                .append("Quantity: ")
                .append(getTotalQuantity() + "\n")
                .append("Sell Price: ")
                .append(getSellPrice() + "\n")
                .append("Cost Price: ")
                .append(getCostPrice() + "\n");

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
