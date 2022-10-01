package seedu.address.model.commission.exceptions;

/**
 * Signals that the operation will result in duplicate Commissions (Commissions are considered duplicates if they have
 * the same identity).
 */
public class DuplicateCommissionException extends RuntimeException {
    public DuplicateCommissionException() {
        super("Operation would result in duplicate commissions");
    }
}
