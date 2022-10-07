package seedu.address.model.item;

import seedu.address.model.item.itemvalidator.ItemUnitValidator;

import static java.util.Objects.requireNonNull;

/**
 * Represents an item unit in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemUnit {

    public final String itemUnit;

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemUnit // instanceof handles nulls
                && itemUnit.equals(((ItemUnit) other).itemUnit)); // state check
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
