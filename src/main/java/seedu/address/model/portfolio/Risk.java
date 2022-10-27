package seedu.address.model.portfolio;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents Risk level of client in the FinBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidRisk(String)}}
 */
public class Risk {
    public static final String MESSAGE_CONSTRAINTS = "Risk level can take any values, and it should not be blank";

    /*
     * The first character of the risk must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Risk}. Risk level can be null
     *
     * @param risk A valid risk.
     */
    public Risk(String risk) {
        if (risk != null && !risk.isEmpty()) {
            checkArgument(isValidRisk(risk), MESSAGE_CONSTRAINTS);
        }
        this.value = risk;

    }

    /**
     * Returns true if a given string is a valid risk.
     */
    public static boolean isValidRisk(String test) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.portfolio.Risk // instanceof handles nulls
                && (value != null && value.equals(((seedu.address.model.portfolio.Risk) other).value))); // state check
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
