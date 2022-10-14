package seedu.address.model.commission;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Commission's fee in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFee(double)}
 */
public class Fee {

    public static final String MESSAGE_CONSTRAINTS =
            "Fee should be at least $0 and not more than $10,000,000.";
    // this max fee constraint is enforced in order to prevent issues that might arise
    // with manipulating data that is too big which might give rise to unexpected behaviours
    public static final Double MAX_FEE = Double.valueOf(10_000_000);
    public final Double fee;

    /**
     * Constructs a {@code Fee}
     *
     * @param fee A fee.
     */
    public Fee(Double fee) {
        requireNonNull(fee);
        checkArgument(isValidFee(fee), MESSAGE_CONSTRAINTS);
        this.fee = fee;
    }

    /**
     * Returns true if a given double is a valid fee.
     * @param test Double being tested.
     * @return Whether the fee is non-negative and not more than the max fee.
     */
    public static boolean isValidFee(double test) {
        return test >= 0 && test <= MAX_FEE;
    }

    @Override
    public String toString() {
        return String.valueOf(fee);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Fee
                && fee.equals(((Fee) other).fee));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fee);
    }


}
