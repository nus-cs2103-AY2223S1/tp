package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.item.itemvalidators.ItemUnitValidator;

/**
 * Represents an item unit in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemUnit {
    private final String itemUnit;

    /**
     * Constructs an {@link ItemUnit}.
     *
     * @param unitString a valid item {@link ItemUnit#itemUnit}.
     */
    public ItemUnit(String unitString) {
        requireNonNull(unitString);
        ItemUnitValidator.validate(unitString);
        itemUnit = unitString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ItemUnit
                && itemUnit.equals(((ItemUnit) other).itemUnit));
    }

    /**
     * Compares two item unit lexicographically. The method returns 0 if the string is equal to the other string.
     * A value less than 0 is returned if the string is less than the other string (less characters) and
     * a value greater than 0 if the string is greater than the other string (more characters).
     *
     * @param other The ItemUnit to compare this ItemUnit against.
     */
    public int compareTo(ItemUnit other) {
        return itemUnit.compareTo(other.itemUnit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return itemUnit.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return itemUnit;
    }
}
