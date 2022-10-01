package seedu.address.model.commission;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Commission in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Commission {

    // Identity fields
    private final Title title;
    private final Fee fee;
    private final Deadline deadline;

    /**
     * Every field must be present and not null.
     */
    public Commission(Title title, Fee fee, Deadline deadline) {
        requireAllNonNull(title, fee, deadline);
        this.title = title;
        this.fee = fee;
        this.deadline = deadline;
    }

    public Title getTitle() {
        return title;
    }

    public Fee getFee() {
        return fee;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns true if both commissions have the same title.
     * This defines a weaker notion of equality between two commissions.
     */
    public boolean isSameCommission(Commission otherCommission) {
        if (otherCommission == this) {
            return true;
        }

        return otherCommission != null
                && otherCommission.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both commissions have the same fields.
     * This defines a stronger notion of equality between two commissions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Commission)) {
            return false;
        }

        Commission otherCommission = (Commission) other;
        return otherCommission.getTitle().equals(getTitle())
                && otherCommission.getFee().equals(getFee())
                && otherCommission.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fee, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Fee: ")
                .append(getFee())
                .append("; Deadline: ")
                .append(getDeadline());
        return builder.toString();
    }

}
