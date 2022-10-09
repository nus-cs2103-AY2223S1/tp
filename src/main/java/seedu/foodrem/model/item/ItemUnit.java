package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.item.itemvalidator.ItemUnitValidator;


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
