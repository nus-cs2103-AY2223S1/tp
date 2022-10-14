package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;
import static seedu.foodrem.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
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

    private final Set<Tag> tagSet;


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
        this.tagSet = new HashSet<>();
    }

    /**
     * Overloaded constructor in item.
     *
     * @param name       Name of the item.
     * @param quantity   Quantity of the item.
     * @param unit       Unit of the item.
     * @param boughtDate Date when the item was purchased.
     * @param expiryDate Date when the item will expire.
     * @param tagSet     The set of tags in item
     */
    public Item(ItemName name,
                ItemQuantity quantity,
                ItemUnit unit,
                ItemBoughtDate boughtDate,
                ItemExpiryDate expiryDate, Set<Tag> tagSet) {
        requireAllNonNull(name, quantity, unit, boughtDate, expiryDate);
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.boughtDate = boughtDate;
        this.expiryDate = expiryDate;
        this.tagSet = tagSet;
    }

    /**
     * Creates and returns a {@code Item} with the tagSet of {@code itemToUntag}
     * edited
     */
    public static Item createUntaggedItem(Item itemToUntag, Tag tag) {
        requireNonNull(itemToUntag);
        requireNonNull(tag);

        checkArgument(itemToUntag.containsTag(tag));

        itemToUntag.removeItemTag(tag);

        return new Item(itemToUntag.getName(),
                itemToUntag.getQuantity(),
                itemToUntag.getUnit(),
                itemToUntag.getBoughtDate(),
                itemToUntag.getExpiryDate(),
                itemToUntag.getTagSet()
        );
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

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    /**
     * Returns true if item contains a certain tag.
     */
    public boolean containsTag(Tag tag) {
        return tagSet.contains(tag);
    }

    /**
     * Adds a tag to the item's tagSet
     */
    public void addItemTag(Tag tag) {
        tagSet.add(tag);
    }

    /**
     * Removes a tag from the item's tagSet
     */
    public void removeItemTag(Tag tag) {
        tagSet.remove(tag);
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
                && otherItem.getExpiryDate().equals(expiryDate)
                && otherItem.getTagSet().equals(tagSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, quantity, unit, boughtDate, expiryDate, tagSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tagsString = tagSet.stream().map(Tag::getName).collect(Collectors.joining(", "));
        return String.format("Name: %s\nQuantity: %s%s\nBought Date: %s\nExpiry Date: %s\nTags: {%s}\n",
                name,
                quantity,
                String.valueOf(unit).isBlank() ? "" : " " + unit,
                String.valueOf(boughtDate).isBlank() ? "Not Set" : boughtDate,
                String.valueOf(expiryDate).isBlank() ? "Not Set" : expiryDate,
                tagsString);
    }
}
