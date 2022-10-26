package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.item.itemvalidators.ItemNameValidator;

/**
 * Represents an item name in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemName {
    private final String itemName;

    /**
     * Constructs an {@link ItemName}.
     *
     * @param name a valid item {@link ItemName#itemName}.
     */
    public ItemName(String name) {
        requireNonNull(name);
        ItemNameValidator.validate(name);
        itemName = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ItemName
                && itemName.equals(((ItemName) other).itemName));
    }

    /**
     * Compares two item names lexicographically. The method returns 0 if the string is equal to the other string.
     * A value less than 0 is returned if the string is less than the other string (fewer characters) and
     * a value greater than 0 if the string is greater than the other string (more characters).
     *
     * @param other The ItemName to compare this ItemName against.
     */
    public int compareTo(ItemName other) {
        return itemName.compareTo(other.itemName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return itemName.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return itemName;
    }
}
