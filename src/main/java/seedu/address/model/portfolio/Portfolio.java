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
    private final Set<Note> note = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Portfolio(Risk risk, Set<Plan> plan, Set<Note> note) {
        this.risk = risk;
        this.plans.addAll(plan);
        this.plans.remove(new Plan(""));
        this.note.addAll(note);
        this.note.remove(new Note(""));
    }

    public Risk getRisk() {
        return risk;
    }

    public Set<Plan> getPlans() {
        return Collections.unmodifiableSet(plans);
    }

    public Set<Note> getNotes() {
        return Collections.unmodifiableSet(note);
    }

    /**
     * Displays the message in command result
     *
     * @return string that shows the risk level and current plans
     */
    public String display() {
        String str = "\nRisk Level: " + this.getRisk().toString() + "\nPlans: " + this.getPlans().toString();
        return str;
    }

    /**
     * Returns true if both meetings have the same time and location fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Portfolio)) {
            return false;
        }

        Portfolio otherPortfolio = (Portfolio) other;
        return otherPortfolio.getRisk().equals(getRisk())
            && otherPortfolio.getPlans().equals(getPlans())
            && otherPortfolio.getNotes().equals(getNotes());
    }
}
