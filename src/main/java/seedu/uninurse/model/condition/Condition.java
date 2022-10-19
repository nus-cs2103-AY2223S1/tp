package seedu.uninurse.model.condition;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's medical condition.
 * Guarantees: immutable; is valid as declared in {@link #isValidCondition(String)}
 */
public class Condition {

    public static final String MESSAGE_CONSTRAINTS = "Conditions can take any values, and it should not be blank";

    /*
     * The first character of the condition must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String condition;

    /**
     * Constructs a {@code Condition}.
     *
     * @param condition A valid condition.
     */
    public Condition(String condition) {
        requireNonNull(condition);
        checkArgument(isValidCondition(condition), MESSAGE_CONSTRAINTS);
        this.condition = condition;
    }

    /**
     * Returns true if a given string is a valid condition.
     */
    public static boolean isValidCondition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return condition;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Condition // instanceof handles nulls
                && condition.equals(((Condition) other).condition)); // state check
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
    }
}
