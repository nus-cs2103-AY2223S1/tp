package seedu.trackascholar.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's application status in TrackAScholar.
 * Guarantees: immutable; is valid as declared in {@link #isValidApplicationStatus(String)}
 */
public class ApplicationStatus implements Comparable<ApplicationStatus> {

    public static final String MESSAGE_CONSTRAINTS =
            "ApplicationStatus can only be 3 values (PENDING, ACCEPTED or REJECTED), and it should not be blank";

    public static final String MESSAGE_STATUS_REJECTION =
            "Completed ApplicationStatus can only be 2 values (ACCEPTED or REJECTED), and it should not be blank";

    public static final String PENDING = "pending";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";

    private final String status;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param status A status.
     */
    public ApplicationStatus(String status) {
        requireNonNull(status);
        String statusToLowerCase = status.toLowerCase();
        checkArgument(isValidApplicationStatus(statusToLowerCase), MESSAGE_CONSTRAINTS);
        if (statusToLowerCase.equals(ACCEPTED)) {
            this.status = ACCEPTED;
        } else if (statusToLowerCase.equals(REJECTED)) {
            this.status = REJECTED;
        } else {
            this.status = PENDING;
        }
    }

    /**
     * Returns true only if a given string is a valid application status.
     */
    public static boolean isValidApplicationStatus(String test) {
        return test.equalsIgnoreCase(PENDING)
                || test.equalsIgnoreCase(ACCEPTED)
                || test.equalsIgnoreCase(REJECTED);
    }

    /**
     * Returns true only if a given string is a valid and completed application status.
     */
    public static boolean isCompletedApplicationStatus(String test) {
        return test.equalsIgnoreCase(ACCEPTED)
                || test.equalsIgnoreCase(REJECTED);
    }

    /**
     * Returns the status of applicant's scholarship application.
     */
    public String getStatusOfApplication() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationStatus // instanceof handles nulls
                && status.equals(((ApplicationStatus) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    @Override
    public int compareTo(ApplicationStatus other) {
        if (this.equals(other)) {
            return 0;
        } else if (this.status.equals(PENDING) || other.status.equals(REJECTED)) {
            return -1;
        } else {
            return 1;
        }
    }
}
