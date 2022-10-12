package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.model.item.itemvalidator.ItemNameValidator;

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
        return other == this // short circuit if same object
                || (other instanceof ItemName // instanceof handles nulls
                && itemName.equals(((ItemName) other).itemName)); // state check
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
