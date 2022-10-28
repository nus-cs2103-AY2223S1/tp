package longtimenosee.model.policy;

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
     * @param premium Must be a valid yearly premium.
     * @param startDate Must be a valid date, after 1900, and before 2100.
     * @param endDate Must be a valid date, after startDate and 1900, and before 2100.
     */
    public AssignedPolicy(Policy policy, Premium premium, PolicyDate startDate, PolicyDate endDate) {
        requireAllNonNull(policy, premium, startDate, endDate);
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

    public boolean isSamePolicy(Policy policyToCheck) {
        return this.policy.isSamePolicy(policyToCheck);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.policy.toString())
                .append("\n" + "Premium: ")
                .append(getPremium() + "\n")
                .append("Start: ")
                .append(getStartDate() + "\n")
                .append("End: ")
                .append(getEndDate() + "\n");
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
