package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

// SHOULD ABSTRACT OUT LOGIC FROM ITEM NAME, UNIT, TAG NAME COMMON TO VALIDATION

/**
 * Represents an item unit in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemUnit {

    public final String itemUnit;

    // Validation for characters used in unit
    // TODO: Change validation to match FoodREM
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final String MESSAGE_FOR_INVALID_CHARACTERS =
            "Item unit should only contain alphanumeric characters and spaces, and it should not be blank";

    // Validation for unit length
    private static final int MAX_LENGTH = 10;
    private static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("Item unit should not exceed %d characters", MAX_LENGTH);

    /**
     * Constructs an {@link ItemUnit}.
     *
     * @param unit a valid item {@link ItemUnit#itemUnit}.
     */
    public ItemUnit(String unit) {
        requireNonNull(unit);

        checkArgument(doesUnitContainInvalidCharacters(unit), MESSAGE_FOR_INVALID_CHARACTERS);
        checkArgument(isUnitLengthMoreThanMaxLength(unit), MESSAGE_FOR_NAME_TOO_LONG);

        itemUnit = unit;
    }

    /**
     * Returns true if a given string contains invalid characters, false otherwise.
     *
     * @param itemUnit a string that represents the {@link ItemUnit#itemUnit}.
     */
    private static boolean doesUnitContainInvalidCharacters(String itemUnit) {
        return !itemUnit.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if an item unit has a length more than {@link ItemUnit#MAX_LENGTH}, false otherwise.
     *
     * @param itemUnit a string that represents the {@link ItemUnit#itemUnit}.
     */
    private static boolean isUnitLengthMoreThanMaxLength(String itemUnit) {
        return itemUnit.length() > MAX_LENGTH;
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
