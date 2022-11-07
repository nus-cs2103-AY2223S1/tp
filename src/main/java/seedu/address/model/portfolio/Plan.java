package seedu.address.model.portfolio;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the clients' current investment plans in the FinBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidPlan(String)}}
 */
public class Plan {
    public static final String MESSAGE_CONSTRAINTS = "Plan names should be alphanumeric";

    public final String value;

    /**
     * Constructs a {@code Plan}. Plans can be null
     *
     * @param plan A valid plan.
     */
    public Plan(String plan) {
        if (plan != null && !plan.isEmpty()) {
            checkArgument(isValidPlan(plan), MESSAGE_CONSTRAINTS);
        }
        this.value = plan;

    }

    /**
     * Returns true if a given string is a valid plan.
     */
    public static boolean isValidPlan(String test) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Plan // instanceof handles nulls
                && (value != null && value.equals(((Plan) other).value))); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value;
    }

}
