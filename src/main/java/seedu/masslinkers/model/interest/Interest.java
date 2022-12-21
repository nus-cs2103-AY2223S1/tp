package seedu.masslinkers.model.interest;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.util.AppUtil.checkArgument;

/**
 * Represents an Interest in the mass linkers.
 * Guarantees: immutable; name is valid as declared in {@link #isValidInterest(String)}
 */
public class Interest {

    public static final String MESSAGE_CONSTRAINTS = "Interests should be alphanumeric.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String interestName;

    /**
     * Constructs a {@code Interest}.
     *
     * @param interestName A valid interest name.
     */
    public Interest(String interestName) {
        requireNonNull(interestName);
        checkArgument(isValidInterest(interestName), MESSAGE_CONSTRAINTS);
        this.interestName = interestName.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid interest name.
     */
    public static boolean isValidInterest(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interest // instanceof handles nulls
                && interestName.equalsIgnoreCase(((Interest) other).interestName)); // state check
    }

    @Override
    public int hashCode() {
        return interestName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + interestName + ']';
    }

}
