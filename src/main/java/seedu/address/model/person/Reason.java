package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a reason for loan change of a person.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reason {

    public static final String MESSAGE_CONSTRAINTS =
            "The reason cannot be left blank!";

    public static final String VALIDATION_REGEX = "[\\p{ASCII}][\\p{ASCII}]*";

    public final String reason;

    /**
     * Constructor for a Reason object
     *
     * @param reason Reason of the loan change.
     */
    public Reason(String reason) {
        requireNonNull(reason);
        checkArgument(isValidReason(reason), MESSAGE_CONSTRAINTS);
        this.reason = reason;
    }

    public static boolean isValidReason(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return reason;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reason // instanceof handles nulls
                && reason.equals(((Reason) other).reason)); // state check
    }

    @Override
    public int hashCode() {
        return reason.hashCode();
    }
}
