package seedu.address.model.bill;

import java.util.function.Predicate;

/**
 * Tests that a {@code Bill}'s {@code Payment Status} matches the given payment status.
 */
public class PaymentStatusPredicate implements Predicate<Bill> {
    private final String paymentStatus;

    public PaymentStatusPredicate(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean test(Bill bill) {
        return bill.getPaymentStatus().toString().equals(paymentStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof seedu.address.model.bill.PaymentStatusPredicate
                // state check
                && paymentStatus.equals(((seedu.address.model.bill.PaymentStatusPredicate) other).paymentStatus));
    }
}


