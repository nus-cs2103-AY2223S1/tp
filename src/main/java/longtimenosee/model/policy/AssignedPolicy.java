package longtimenosee.model.policy;

import static longtimenosee.commons.util.AppUtil.checkArgument;
import static longtimenosee.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * An assigned policy is an encapsulation of the necessary details
 * of an insurance policy undertaken by a client.
 */
public class AssignedPolicy {

    public static final String MESSAGE_DATE_CONSTRAINTS = "End date should not be before Start date";

    public final Policy policy;
    public final Premium premium;
    public final PolicyDate startDate;
    public final PolicyDate endDate;

    /**
     * Construct an AssignedPolicy object.
     * @param policy
     * @param premium
     * @param startDate
     * @param endDate
     */
    public AssignedPolicy(Policy policy, Premium premium, PolicyDate startDate, PolicyDate endDate) {
        requireAllNonNull(policy, premium, startDate, endDate);
        checkArgument(isChronological(startDate, endDate), MESSAGE_DATE_CONSTRAINTS);
        this.startDate = startDate;
        this.endDate = endDate;
        this.premium = premium;
        this.policy = policy;
    }

    /**
     * Returns true if given start date is before the end date.
     */
    public static boolean isChronological(PolicyDate startDate, PolicyDate endDate) {
        return startDate.getDate().isBefore(endDate.getDate());
    }
    /**
     * Returns the duration between start date and end date.
     */
    public Period getDuration(LocalDate date) {
        Period diff = Period.between(LocalDate.parse(startDate.getDate().toString()),
                LocalDate.parse(date.toString()));
        return diff;
    }

    public Policy getPolicy() {
        return this.policy;
    }

    public Premium getPremium() {
        return this.premium;
    }

    public PolicyDate getStartDate() {
        return this.startDate;
    }

    public PolicyDate getEndDate() {
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
    @Override
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
        return Objects.hash(this.getPolicy());
    }


}
