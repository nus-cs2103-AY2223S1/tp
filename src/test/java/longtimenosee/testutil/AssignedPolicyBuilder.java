package longtimenosee.testutil;

import static longtimenosee.testutil.TypicalPolicies.PRULIFE;

import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.PolicyDate;
import longtimenosee.model.policy.Premium;

/**
 * A utility class to help with building Person objects.
 */
public class AssignedPolicyBuilder {

    public static final String DEFAULT_PREMIUM = "200";
    public static final String DEFAULT_START_DATE = "2010-10-10";
    public static final String DEFAULT_END_DATE = "2010-10-10";
    public static final Policy DEFAULT_POLICY = PRULIFE;


    private Policy policy;
    private Premium premium;
    private PolicyDate startDate;
    private PolicyDate endDate;

    /**
     * Creates a {@code PolicyBuilder} with the default details.
     */
    public AssignedPolicyBuilder() {
        policy = DEFAULT_POLICY;
        premium = new Premium(DEFAULT_PREMIUM);
        startDate = new PolicyDate(DEFAULT_START_DATE);
        endDate = new PolicyDate(DEFAULT_END_DATE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public AssignedPolicyBuilder(AssignedPolicy assignedPolicyToCopy) {
        policy = assignedPolicyToCopy.getPolicy();
        premium = assignedPolicyToCopy.getPremium();
        startDate = assignedPolicyToCopy.getStartDate();
        endDate = assignedPolicyToCopy.getEndDate();
    }

    /**
     * Sets the {@code Premium} of the {@code AssignedPolicy} that we are building.
     */
    public AssignedPolicyBuilder withPremium(String premium) {
        this.premium = new Premium(premium);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code AssignedPolicy} that we are building.
     */
    public AssignedPolicyBuilder withStartDate(String startDate) {
        this.startDate = new PolicyDate(startDate);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code AssignedPolicy} that we are building.
     */
    public AssignedPolicyBuilder withEndDate(String endDate) {
        this.endDate = new PolicyDate(endDate);
        return this;
    }

    /**
     * Sets the {@code Policy} of the {@code AssignedPolicy} that we are building.
     */
    public AssignedPolicyBuilder withPolicy(Policy policy) {
        this.policy = policy;
        return this;
    }

    /**
     * Main builds a copy of the current client stored.
     * @return person
     */
    public AssignedPolicy build() {
        return new AssignedPolicy(policy, premium, startDate, endDate);
    }
}
