package tracko.model.items;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.AppUtil.checkArgument;

/**
 * Represents an item's name.
 */
public class ItemName {
    public static final String MESSAGE_CONSTRAINTS =
            "Item names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String itemName;

    /**
     * Constructs an {@code ItemName}.
     *
     * @param itemName The name of the item.
     */
    public ItemName(String itemName) {
        requireNonNull(itemName);
        checkArgument(isValidItemName(itemName), MESSAGE_CONSTRAINTS);
        this.itemName = itemName;
    }

    public static boolean isValidItemName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return itemName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemName // instanceof handles nulls
                && itemName.equals(((ItemName) other).itemName)); // state check
    }

    @Override
    public int hashCode() {
        return itemName.hashCode();
    }

}
