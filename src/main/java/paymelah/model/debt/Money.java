package paymelah.model.debt;

import static java.util.Objects.requireNonNull;
import static paymelah.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a Debt's money amount in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoney(String)}
 */
public class Money implements Comparable<Money> {
    public static final String MESSAGE_CONSTRAINTS =
            "Money amounts should use numbers to represent the amount in dollars,"
                    + " separating dollar and cent values with a '.'"
                    + "\nEnd with + to add GST to the amount, or ++ to add both GST and service charge.";
    public static final String VALIDATION_REGEX = "\\$?\\d+(\\.\\d{1,2})?\\s*(\\+{1,2})?";
    public static final int CENTS_PRECISION = 2;
    public static final BigDecimal GST_MULTIPLIER = new BigDecimal("1.07");
    public static final BigDecimal SERVICE_CHARGE_MULTIPLIER = new BigDecimal("1.10");

    private final BigDecimal value;

    /**
     * Constructs a {@code Money}.
     *
     * @param money A valid money amount.
     */
    public Money(String money) {
        requireNonNull(money);
        checkArgument(isValidMoney(money), MESSAGE_CONSTRAINTS);
        if (money.startsWith("$")) {
            money = money.substring(1);
        }

        int plusCount = 0;
        while (money.endsWith("+")) {
            plusCount += 1;
            money = money.substring(0, money.length() - 1).trim();
        }

        BigDecimal value = new BigDecimal(money).setScale(CENTS_PRECISION);
        if (plusCount == 1) {
            value = value.multiply(GST_MULTIPLIER).setScale(CENTS_PRECISION, RoundingMode.CEILING);
        } else if (plusCount == 2) {
            value = value.multiply(SERVICE_CHARGE_MULTIPLIER).multiply(GST_MULTIPLIER)
                    .setScale(CENTS_PRECISION, RoundingMode.CEILING);
        } else if (plusCount != 0) {
            assert false : ">2 '+' not detected as invalid Money input.";
        }

        this.value = value;
    }

    /**
     * Returns true if a given string is a valid money amount.
     *
     * @param test The string to test for validity.
     * @return true if the given string is a valid money amount.
     */
    public static boolean isValidMoney(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    protected BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Money // instanceof handles nulls
                && value.equals(((Money) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Money o) {
        return this.getValue().compareTo(o.getValue());
    }
}
