package seedu.address.model.portfolio;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a portfolio of a client in the FinBook.
 * Guarantees: details are immutable.
 */
public class Portfolio {
    private final Risk risk;
    private final Set<Plan> plans = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Portfolio(Risk risk, Set<Plan> plan) {
        this.risk = risk;
        this.plans.addAll(plan);
    }

    public Risk getRisk() {
        return risk;
    }

    public Set<Plan> getPlans() {
        return Collections.unmodifiableSet(plans);
    }

    /**
     * Displays the message in command result
     * @return string that shows the risk level and current plans
     */
    public String display() {
        String str = "\nRisk Level: " + this.getRisk().toString() + "\nPlans: " + this.getPlans().toString();
        return str;
    }
}
