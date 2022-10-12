package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents an Internship's application status.
 * Guarantees: details are present and not null
 */
public class Status {
    public static final String MESSAGE_CONSTRAINTS = "Status can be of 3 types: Progress, Offered, Rejected";

    /*
     * The first character of the status must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String status;

    /**
     * Constructs an {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        switch (status.toUpperCase()) {
        case "OFFERED":
            this.status = "Offered";
            break;
        case "PROGRESS":
            this.status = "Progress";
            break;
        case "REJECTED":
            this.status = "Rejected";
            break;
        default:
            this.status = "Progress";
        }
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX)
                && (Objects.equals(test.toUpperCase(), "OFFERED")
                || Objects.equals(test.toUpperCase(), "PROGRESS")
                || Objects.equals(test.toUpperCase(), "REJECTED"));
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && status.equals(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
