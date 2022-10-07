package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an item name in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemName {

    public final String itemName;

    // Validation for characters used in name
    // TODO: Change validation to match FoodREM
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS =
            "Item name should only contain alphanumeric characters and spaces, and it should not be blank";

    // Validation for name length
    private static final int MAX_LENGTH = 200;
    private static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("Item name should not exceed %d characters", MAX_LENGTH);
    private static final String MESSAGE_FOR_NAME_IS_BLANK =
            "Item name should not be blank";

    /**
     * Constructs an {@link ItemName}.
     *
     * @param name a valid item {@link ItemName#itemName}.
     */
    public ItemName(String name) {
        requireNonNull(name);

        checkArgument(doesNameContainInvalidCharacters(name), MESSAGE_FOR_INVALID_CHARACTERS);
        checkArgument(isNameLengthMoreThanMaxLength(name), MESSAGE_FOR_NAME_TOO_LONG);
        checkArgument(isNameBlank(name), MESSAGE_FOR_NAME_IS_BLANK);

        itemName = name;
    }

    /**
     * Returns true if a given string contains invalid characters, false otherwise.
     *
     * @param itemName a string that represents the {@link ItemName#itemName}.
     */
    private static boolean doesNameContainInvalidCharacters(String itemName) {
        return !itemName.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if an item name has a length more than {@link ItemName#MAX_LENGTH}, false otherwise.
     *
     * @param itemName a string that represents the {@link ItemName#itemName}.
     */
    private static boolean isNameLengthMoreThanMaxLength(String itemName) {
        return itemName.length() > MAX_LENGTH;
    }

    /**
     * Returns true if an item name is {@link String#isEmpty()}, false otherwise.
     *
     * @param itemName a string that represents the {@link ItemName#itemName}.
     */
    private static boolean isNameBlank(String itemName) {
        return itemName.isEmpty();
    }

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
