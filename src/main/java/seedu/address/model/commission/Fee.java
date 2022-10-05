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
            "Fees should be at least 0.";

    public final double fee;

    /**
     * Constructs a {@code Fee}
     *
     * @param fee A fee.
     */
    public Fee(double fee) {
        requireNonNull(fee);
        checkArgument(isValidFee(fee), MESSAGE_CONSTRAINTS);
        this.fee = fee;
    }

    /**
     * Returns true if a given double is a valid fee.
     * @param test Double being tested.
     * @return Whether the fee is non-negative.
     */
    public static boolean isValidFee(double test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return String.valueOf(fee);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Fee
                && fee == ((Fee) other).fee);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fee);
    }


}
