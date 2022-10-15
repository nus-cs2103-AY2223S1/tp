package tracko.model.items;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import tracko.model.tag.Tag;

/**
 * Represents an item in the inventory list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item {
    public final ItemName itemName;
    public final Description description;
    public final Quantity quantity;
    public final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs an {@code item}.
     *
     * @param itemName The name of the item.
     * @param description The description of the item.
     * @param quantity The quantity of the item.
     * @param tags The tags associated with the item.
     */
    public Item(ItemName itemName, Description description, Quantity quantity, Set<Tag> tags) {
        requireAllNonNull(itemName, description, tags);
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.tags.addAll(tags);
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
                && otherItem.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName, description, quantity, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getItemName() + "\n")
                .append("Description: ")
                .append(getDescription() + "\n")
                .append("Quantity: ")
                .append(getQuantity());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
