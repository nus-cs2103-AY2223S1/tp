package seedu.address.model.person;

import seedu.address.model.ShallowCopyable;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class LoanHistory implements ShallowCopyable {

    private final Loan loanChange;

    private final Reason reason;
    
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
