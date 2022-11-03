package tracko.model.item;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.AppUtil.checkArgument;

/**
 * Represents an item's name.
 */
public class ItemName {
    public static final String MESSAGE_CONSTRAINTS =
            "Item names should only contain alphanumeric characters and spaces,"
                    + " and it should be more than 1 character long";

    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9 ].*$";

    public final String value;

    /**
     * Constructs an {@code ItemName}.
     *
     * @param value The name of the item.
     */
    public ItemName(String value) {
        requireNonNull(value);
        checkArgument(isValidItemName(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    public static boolean isValidItemName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemName // instanceof handles nulls
                && value.equalsIgnoreCase(((ItemName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
