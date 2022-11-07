package seedu.condonery.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's price in Condonery.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS = "Prices can take any positive integer, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d+([,]\\d+)*$";

    public final String value;

    public final Integer amount;

    /**
     * Constructs an {@code Address}.
     *
     * @param price A valid address.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        amount = Integer.parseInt(price.replaceAll(",", ""));
        value = this.toString();
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && amount.equals(((Price) other).amount)); // state check
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
