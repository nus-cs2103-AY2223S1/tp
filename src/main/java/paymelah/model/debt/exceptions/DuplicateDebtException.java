package paymelah.model.debt.exceptions;

/**
 * Signals that the operation will result in duplicate Debts (Debts are considered duplicates if they have the same
 * description, money, date and time).
 */
public class DuplicateDebtException extends RuntimeException {
    public DuplicateDebtException() {
        super("Operation would result in duplicate debts");
    }
}
