package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.text.DecimalFormat;
import java.util.function.BiFunction;

import seedu.foodrem.model.item.itemvalidators.ItemQuantityValidator;

/**
 * Represents an item quantity in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemQuantity {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.####");
    private static final double DEFAULT_QUANTITY = 0;

    private final double itemQuantity;

    /**
     * {@inheritDoc}
     */
    public ItemQuantity(String itemQuantityString) {
        requireNonNull(itemQuantityString);
        if (itemQuantityString.isEmpty()) {
            itemQuantity = DEFAULT_QUANTITY;
            return;
        }
        ItemQuantityValidator.validate(itemQuantityString);
        itemQuantity = Double.parseDouble(itemQuantityString);
    }

    /**
     * Returns an itemQuantity after performing an arithmetic operation on them.
     */
    public static ItemQuantity performArithmeticOperation(ItemQuantity itemQuantity1,
                                                          ItemQuantity itemQuantity2,
                                                          BiFunction<Double, Double, Double> op) {
        double newQuantity = op.apply(itemQuantity1.itemQuantity, itemQuantity2.itemQuantity);
        return new ItemQuantity(DECIMAL_FORMAT.format(newQuantity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ItemQuantity
                && itemQuantity == ((ItemQuantity) other).itemQuantity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Double.hashCode(itemQuantity);
    }

    /**
     * Compares two item quantities. The method returns 0 if the quantity is equal to the other quantity.
     * A value less than 0 is returned if the quantity is less than the other quantity and
     * a value greater than 0 if the quantity is greater than the other quantity.
     *
     * @param other The ItemQuantity to compare this ItemQuantity against.
     */
    public int compareTo(ItemQuantity other) {
        return Double.compare(itemQuantity, other.itemQuantity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return DECIMAL_FORMAT.format(itemQuantity);
    }
}
