package longtimenosee.testutil;

import java.util.HashSet;
import java.util.Set;

import longtimenosee.model.policy.Commission;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.Title;
import longtimenosee.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PolicyBuilder {

    public static final String DEFAULT_TITLE = "PruShield";
    public static final String DEFAULT_COMPANY = "PRU";
    public static final String DEFAULT_COMMISSION = "10% 5% 1.5%";


    private Title title;
    private Company company;
    private Commission commission;
    private Set<Coverage> coverages;

    /**
     * Creates a {@code PolicyBuilder} with the default details.
     */
    public PolicyBuilder() {
        title = new Title(DEFAULT_TITLE);
        company = new Company(DEFAULT_COMPANY);
        commission = new Commission(DEFAULT_COMMISSION);
        coverages = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PolicyBuilder(Policy policyToCopy) {
        title = policyToCopy.getTitle();
        company = policyToCopy.getCompany();
        commission = policyToCopy.getCommission();
        coverages = new HashSet<>(policyToCopy.getCoverages());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PolicyBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PolicyBuilder withCoverages(String ... tags) {
        this.coverages = SampleDataUtil.getCoverageSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PolicyBuilder withCommission(String commissions) {
        this.commission = new Commission(commissions);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PolicyBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Main builds a copy of the current client stored.
     * @return person
     */

    public Policy build() {
        return new Policy(title, company, commission, coverages);
    }
}
