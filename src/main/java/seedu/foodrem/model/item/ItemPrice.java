package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.text.DecimalFormat;

import seedu.foodrem.model.item.itemvalidators.ItemPriceValidator;

/**
 * Represents an item price in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemPrice {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##");
    private static final double DEFAULT_PRICE = 0;

    private final double itemPrice;
    /**
     * {@inheritDoc}
     */
    public ItemPrice(String itemPriceString) {
        requireNonNull(itemPriceString);
        if (itemPriceString.isEmpty()) {
            itemPrice = DEFAULT_PRICE;
            return;
        }
        ItemPriceValidator.validate(itemPriceString);
        itemPrice = Double.parseDouble(itemPriceString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemPrice // instanceof handles nulls
                && itemPrice == ((ItemPrice) other).itemPrice); // state check
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Double.hashCode(itemPrice);
    }

    /**
     * Compares two item price. The method returns 0 if the item price is equal to the other item price.
     * A value less than 0 is returned if the item price is less than the other item price and
     * a value greater than 0 if the item price is greater than the other item price.
     *
     * @param other The ItemPrice to compare this ItemPrice against.
     */
    public int compareTo(ItemPrice other) {
        return Double.compare(itemPrice, other.itemPrice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return DECIMAL_FORMAT.format(itemPrice);
    }
}
