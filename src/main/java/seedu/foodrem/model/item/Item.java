package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.foodrem.model.tag.Tag;

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
    private final ItemPrice price;
    private final ItemRemark remarks;
    private final Set<Tag> tagSet;

    /**
     * Constructs an item.
     *
     * @param name       Name of the item.
     * @param quantity   Quantity of the item.
     * @param unit       Unit of the item.
     * @param boughtDate Date when the item was purchased.
     * @param expiryDate Date when the item will expire.
     * @param price      Price of one unit of the item.
     * @param remarks    Remarks related to the item.
     * @param tagSet     The set of tags in item.
     */
    public Item(ItemName name,
                ItemQuantity quantity,
                ItemUnit unit,
                ItemBoughtDate boughtDate,
                ItemExpiryDate expiryDate,
                ItemPrice price,
                ItemRemark remarks,
                Set<Tag> tagSet) {
        requireAllNonNull(name, quantity, unit, boughtDate, expiryDate, price, remarks, tagSet);

        // Bought date is after today
        if (!boughtDate.isNotSet() && boughtDate.isAfterDate(LocalDate.now())) {
            throw new IllegalArgumentException("The item bought date should not be in the future.");
        }

        // Same Bought date and Expiry date is accepted
        if (!boughtDate.isNotSet() && !expiryDate.isNotSet() && boughtDate.isAfterDate(expiryDate)) {
            throw new IllegalArgumentException("The item bought date should not be after the item expiry date.");
        }

        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.boughtDate = boughtDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.remarks = remarks;
        this.tagSet = tagSet;
    }

    /**
     * Creates and returns an {@code Item} with {@code tags}.
     *
     * @param item the item to be created.
     * @param tags the tags to be added to the item.
     * @return an item with the tags provided.
     */
    public static Item createItemWithTags(Item item, Set<Tag> tags) {
        requireNonNull(item);
        requireNonNull(tags);

        return new Item(item.getName(),
                item.getQuantity(),
                item.getUnit(),
                item.getBoughtDate(),
                item.getExpiryDate(),
                item.getPrice(),
                item.getRemarks(),
                tags
        );
    }

    /**
     * Returns the item name.
     */
    public ItemName getName() {
        assert name != null;
        return name;
    }

    /**
     * Returns the item quantity.
     */
    public ItemQuantity getQuantity() {
        assert quantity != null;
        return quantity;
    }

    /**
     * Returns the item unit.
     */
    public ItemUnit getUnit() {
        assert unit != null;
        return unit;
    }

    /**
     * Returns the item bought date.
     */
    public ItemBoughtDate getBoughtDate() {
        assert boughtDate != null;
        return boughtDate;
    }

    /**
     * Returns the item expiry date.
     */
    public ItemExpiryDate getExpiryDate() {
        assert expiryDate != null;
        return expiryDate;
    }

    /**
     * Returns the item price.
     */
    public ItemPrice getPrice() {
        assert price != null;
        return price;
    }

    /**
     * Returns the item remarks.
     */
    public ItemRemark getRemarks() {
        assert remarks != null;
        return remarks;
    }

    /**
     * Returns true if the item has expired and false otherwise
     *
     * @return The total value of purchasing the specified units of the item.
     */
    public boolean isExpired() {
        assert expiryDate != null;
        return expiryDate.isAfterOrOnDate(LocalDate.now());
    }

    /**
     * Returns true if the item quantity is not zero and false otherwise.
     *
     * @return The total value of purchasing the specified units of the item.
     */
    public boolean hasNonZeroQuantity() {
        assert quantity != null;
        return !quantity.isZero();
    }

    /**
     * Returns the item value.
     *
     * @return The total value of purchasing the specified units of the item.
     */
    public double getItemValue() {
        assert price != null && quantity != null;
        // TODO: Possibly refactor to avoid using getter methods in ItemPrice and ItemQuantity fields
        return price.getItemPrice() * quantity.getItemQuantity();
    }

    /**
     * Returns the tags of the item.
     *
     * @return a new set containing the tags of this item.
     */
    public Set<Tag> getTagSet() {
        assert tagSet != null;
        // Instantiate new set to preserve immutability of item.
        return new HashSet<>(tagSet);
    }

    /**
     * Returns {@code true} if both items have the same name.
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
     * Returns {@code true} if both items have the same name and data fields.
     * This defines a stronger notion of equality between two items.
     *
     * @param other an object to compare against.
     * @return true if both items have the same name, {@code false} otherwise.
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
        return otherItem.name.equals(name)
                && otherItem.quantity.equals(quantity)
                && otherItem.unit.equals(unit)
                && otherItem.boughtDate.equals(boughtDate)
                && otherItem.expiryDate.equals(expiryDate)
                && otherItem.price.equals(price)
                && otherItem.remarks.equals(remarks)
                && otherItem.tagSet.equals(tagSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // Use this method for custom fields hashing instead of implementing your own
        // Defensive programming to guard against possible null values.
        List<Object> attributesToHash = new ArrayList<>();
        if (name != null) {
            attributesToHash.add(name);
        }
        if (quantity != null) {
            attributesToHash.add(quantity);
        }
        if (unit != null) {
            attributesToHash.add(unit);
        }
        if (boughtDate != null) {
            attributesToHash.add(boughtDate);
        }
        if (expiryDate != null) {
            attributesToHash.add(expiryDate);
        }
        if (price != null) {
            attributesToHash.add(price);
        }
        if (remarks != null) {
            attributesToHash.add(remarks);
        }
        if (tagSet != null) {
            attributesToHash.add(tagSet);
        }
        return Objects.hash(attributesToHash.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tagsString = tagSet.stream().map(Tag::getName).collect(Collectors.joining(", "));
        return String.format("Name: %s\nQuantity: %s%s\nBought Date: %s\n"
                        + "Expiry Date: %s\nPrice: %s\nRemarks: %s\nTags: {%s}\n",
                name,
                quantity,
                String.valueOf(unit).isBlank() ? "" : " " + unit,
                boughtDate.isNotSet() ? "Not Set" : boughtDate,
                expiryDate.isNotSet() ? "Not Set" : expiryDate,
                "$" + price,
                String.valueOf(remarks).isBlank() ? "No Remarks" : remarks,
                tagsString);
    }
}
