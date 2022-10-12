package seedu.address.model.person;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;

/**
 * An assigned policy is an encapsulation of the necessary details
 * of an insurance policy undertaken by a client.
 */
public class AssignedPolicy {
    public final Policy policy;
    public final Premium premium;
    public final LocalDate startDate;
    public final LocalDate endDate;

    /**
     * Construct an AssignedPolicy object.
     * @param policy
     * @param premium
     * @param startDate
     * @param endDate
     */
    public AssignedPolicy(Policy policy, Premium premium, LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.premium = premium;
        this.policy = policy;
    }

    public Policy getPolicy() {
        return this.policy;
    }

    public Premium getPremium() {
        return this.premium;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.policy.toString())
                .append(", Premium: ")
                .append(getPremium())
                .append(", Start: ")
                .append(getStartDate())
                .append(", End: ")
                .append(getEndDate());
        return sb.toString();
    }

    /**
     * Returns true if both AssignedPolicies have the same policy.
     * This defines a weaker notion of equality between two AssignedPolicies.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignedPolicy)) {
            return false;
        }

        AssignedPolicy otherAssignedPolicy = (AssignedPolicy) other;
        return otherAssignedPolicy != null
                && otherAssignedPolicy.getPolicy().equals(getPolicy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPolicy(), this.getPremium(), this.getStartDate(), this.getEndDate());
    }


}
