package seedu.foodrem.model.item;

import static seedu.foodrem.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents an Item in FoodREM.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item {

    // Identity fields
    private final ItemName name;

    // Data fields
    private final ItemQuantity quantity;
    private final ItemUnit unit;
    private final ItemBoughtDate boughtDate;
    private final ItemExpiryDate expiryDate;
    // TODO: Implement Item with Tags

    /**
     * Constructs an item.
     *
     * @param name       Name of the item.
     * @param quantity   Quantity of the item.
     * @param unit       Unit of the item.
     * @param boughtDate Date when the item was purchased.
     * @param expiryDate Date when the item will expire.
     */
    public Item(ItemName name,
                ItemQuantity quantity,
                ItemUnit unit,
                ItemBoughtDate boughtDate,
                ItemExpiryDate expiryDate) {
        requireAllNonNull(name, quantity, unit, boughtDate, expiryDate);
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.boughtDate = boughtDate;
        this.expiryDate = expiryDate;
    }

    public ItemName getName() {
        return name;
    }

    public ItemQuantity getQuantity() {
        return quantity;
    }

    public ItemUnit getUnit() {
        return unit;
    }

    public ItemBoughtDate getBoughtDate() {
        return boughtDate;
    }

    public ItemExpiryDate getExpiryDate() {
        return expiryDate;
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
                && otherItem.getName().equals(name);
    }

    /**
     * Returns true if both items have the same name and data fields.
     * This defines a stronger notion of equality between two items.
     *
     * @param other an object to compare against.
     * @return true if both items have the same name, false otherwise.
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
        return otherItem.getName().equals(name)
                && otherItem.getQuantity().equals(quantity)
                && otherItem.getUnit().equals(unit)
                && otherItem.getBoughtDate().equals(boughtDate)
                && otherItem.getExpiryDate().equals(expiryDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, quantity, unit, boughtDate, expiryDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("Name: %s; Quantity: %s%s; Bought Date: %s; Expiry Date: %s;",
                name,
                quantity,
                String.valueOf(unit).isBlank() ? "" : " " + unit,
                String.valueOf(boughtDate).isBlank() ? "Not Set" : boughtDate,
                String.valueOf(expiryDate).isBlank() ? "Not Set" : expiryDate);
    }
}
