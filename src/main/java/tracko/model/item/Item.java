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
public class Item {
    private ItemName itemName;
    private Description description;
    private Quantity quantity;
    private Price sellPrice;
    private Price costPrice;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Constructs an {@code item}.
     * @param itemName The name of the item.
     * @param description The description of the item.
     * @param quantity The quantity of the item.
     * @param tags The tags associated with the item.
     * @param sellPrice The price the item is sold at.
     * @param costPrice The price the item was bought at.
     */
    public Item(ItemName itemName, Description description, Quantity quantity, Set<Tag> tags,
                Price sellPrice, Price costPrice) {
        requireAllNonNull(itemName, description, tags, sellPrice, costPrice);
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.tags.addAll(tags);
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public Description getDescription() {
        return description;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Price getSellPrice() {
        return sellPrice;
    }

    public Price getCostPrice() {
        return costPrice;
    }

    /**
     * Updates the invoked {@code Item} reference with the given {@code Item}'s data.
     */
    public void updateData(Item toCopy) {
        this.itemName = toCopy.itemName;
        this.description = toCopy.description;
        this.quantity = toCopy.quantity;
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
     * Returns true if both items have the same name.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getItemName().equals(getItemName());
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

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getItemName().equals(getItemName())
                && otherItem.getDescription().equals(getDescription())
                && otherItem.getQuantity().equals(getQuantity())
                && otherItem.getTags().equals(getTags())
                && otherItem.getCostPrice().equals(getCostPrice())
                && otherItem.getSellPrice().equals(getCostPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName, description, quantity, tags, sellPrice, costPrice);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Name: ")
                .append(getItemName() + "\n")
                .append("Description: ")
                .append(getDescription() + "\n")
                .append("Quantity: ")
                .append(getQuantity() + "\n")
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
