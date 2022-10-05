package seedu.address.model.item;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Item in FoodREM.
 * TODO: Implement Item with Tags
 */
public class Item {
    public final ItemName name;
    public final ItemQuantity quantity;
    public final ItemDate boughtDate;
    public final ItemDate expiryDate;

    /**
     * Constructs an item.
     * @param name Name of the item.
     * @param quantity Quantity of the item.
     * @param boughtDate Date when the item was purchased.
     * @param expiryDate Date when the item expires.
     * TODO: Make expiryDate an optional field.
     */
    public Item(ItemName name, ItemQuantity quantity, ItemDate boughtDate, ItemDate expiryDate) {
       requireAllNonNull(name, quantity, boughtDate, expiryDate);
       this.name = name;
       this.quantity = quantity;
       this.boughtDate = boughtDate;
       this.expiryDate = expiryDate;
    }

    public ItemName getName() {
        return name;
    }

    public ItemQuantity getQuantity() {
        return quantity;
    }

    public ItemDate getBoughtDate() {
        return boughtDate;
    }

    public ItemDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Returns true if both items have the same identity and data fields.
     * @param other Object to compare against.
     * @return True if both items have the same identity and data fields.
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
        return otherItem.getName().equals(getName());
    }

    /**
     * Hash code of the function.
     * @return A hash code representing the item.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, quantity, boughtDate, expiryDate);
    }

    /**
     * Converts Item to string representation.
     * @return String representation of the Item.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Quantity: ")
                .append(getQuantity())
                .append("; Bought Date: ")
                .append(getBoughtDate())
                .append("; Expiry Date: ")
                .append(getExpiryDate());

        return builder.toString();
    }
}
