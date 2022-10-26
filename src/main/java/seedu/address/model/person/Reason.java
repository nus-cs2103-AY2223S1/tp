package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Reason {

    public static final String MESSAGE_CONSTRAINTS =
            "Content should only contain alphanumeric characters, spaces, special characters, it should not be blank "
                    + "and no maximum length";

    public static final String VALIDATION_REGEX = "[\\p{ASCII}][\\p{ASCII}]*";

    public final String reason;

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
