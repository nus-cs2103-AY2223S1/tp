package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.ShallowCopyable;

/**
 * Represents a noteLoanHistory of a person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class LoanHistory implements ShallowCopyable {

    private final Loan loanChange;

    private final Reason reason;

    /**
     * Constructor for the LoanHistory object
     *
     * @param loanChange Change in loan amount.
     * @param reason Reason for the change.
     */
    public LoanHistory(Loan loanChange, Reason reason) {
        requireAllNonNull(loanChange, reason);
        this.loanChange = loanChange;
        this.reason = reason;
    }

    public Loan getLoanChange() {
        return loanChange;
    }

    public Reason getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LoanHistory)) {
            return false;
        }

        LoanHistory otherHistory = (LoanHistory) other;
        return otherHistory.getLoanChange().equals(getLoanChange())
                && otherHistory.getReason().equals(getReason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanChange, reason);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LoanChange: ")
                .append(getLoanChange())
                .append(", Reason: ")
                .append(getReason());

        return builder.toString();
    }

    @Override
    public LoanHistory shallowCopy() {
        return new LoanHistory(loanChange, reason);
    }
}
