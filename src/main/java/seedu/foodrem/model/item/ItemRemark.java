package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.item.itemvalidators.ItemRemarksValidator;

/**
 * Represents an item remark in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemRemark {
    private final String itemRemark;

    /**
     * Constructs an {@link ItemRemark}.
     *
     * @param name a valid item {@link ItemRemark#itemRemark}.
     */
    public ItemRemark(String name) {
        requireNonNull(name);
        ItemRemarksValidator.validate(name);
        itemRemark = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ItemRemark
                && itemRemark.equals(((ItemRemark) other).itemRemark));
    }

    /**
     * Compares two item remarks lexicographically. The method returns 0 if the string is equal to the other string.
     * A value less than 0 is returned if the string is less than the other string (fewer characters) and
     * a value greater than 0 if the string is greater than the other string (more characters).
     *
     * @param other The ItemName to compare this ItemName against.
     */
    public int compareTo(ItemRemark other) {
        return itemRemark.compareTo(other.itemRemark);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return itemRemark.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return itemRemark;
    }
}
