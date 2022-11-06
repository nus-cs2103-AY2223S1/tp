package seedu.address.model.assignmentdetails;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents Assignment Details in the address book.
 * Guarantees: immutable; assignment details are valid as declared in {@link #areValidAssignmentDetails(String)}
 */
public class AssignmentDetails {

    public static final String MESSAGE_CONSTRAINTS = "Assignment details are optional, but it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String assignmentDetails;

    /**
     * Constructs {@code AssignmentDetails}.
     *
     * @param assignmentDetails Valid assignment details.
     */
    public AssignmentDetails(String assignmentDetails) {
        requireNonNull(assignmentDetails);
        checkArgument(areValidAssignmentDetails(assignmentDetails), MESSAGE_CONSTRAINTS);
        this.assignmentDetails = assignmentDetails;
    }

    /**
     * Returns true if a given string is a valid assignment detail.
     */
    public static boolean areValidAssignmentDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignmentDetails // instanceof handles nulls
                && assignmentDetails.equals(((AssignmentDetails) other).assignmentDetails)); // state check
    }

    @Override
    public int hashCode() {
        return assignmentDetails.hashCode();
    }

    /**
     * Format state as text for viewing
     */
    public String toString() {
        return '[' + assignmentDetails + ']';
    }
}
