package seedu.intrack.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.AppUtil.checkArgument;


/**
 * Represents an Internship's application status.
 * Guarantees: details are present and not null
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Status can be of 3 types: Progress, Offered, Rejected";

    /*
     * The status must be either Offered, Progress, or Rejected. It is case insensitive.
     */
    public static final String VALIDATION_REGEX =
            "[Oo][Ff][Ff][Ee][Rr][Ee][Dd]|[Rr][Ee][Jj][Ee][Cc][Tt][Ee][Dd]|[Pp][Rr][Oo][Gg][Rr][Ee][Ss][Ss]";

    public final String value;

    /**
     * Constructs an {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);

        this.value = status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
