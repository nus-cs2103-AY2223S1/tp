package seedu.studmap.model.student;

import static seedu.studmap.commons.util.AppUtil.checkArgument;
import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

import seedu.studmap.commons.exceptions.IllegalValueException;

/**
 * Represents an Assignment object in StudMap.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAssignmentName(String)}
 */
public class Assignment {

    public static final String MESSAGE_CONSTRAINTS = "Assignment names should consist of "
            + "alphanumerics, space, dash and underscore";
    public static final String MESSAGE_STATUS_CONSTRAINTS = "Assignment marking status should be one of "
            + "new, received or marked";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} \\-_]+";
    /**
     * Represents the possible status the assignment can take.
     */
    public static enum Status {
        MARKED,
        RECEIVED,
        NEW
    }
    public static final String ASSIGNMENT_MARKED = "marked";
    public static final String ASSIGNMENT_RECEIVED = "received";
    public static final String ASSIGNMENT_NEW = "new";

    public final String assignmentName;
    public final Status markingStatus;

    /**
     * Constructs an {@code Assignment} object.
     *
     * @param assignmentName A valid assignment name.
     * @param markingStatus A status representing whether the assignment has been marked
     */
    public Assignment(String assignmentName, Status markingStatus) {
        requireAllNonNull(assignmentName, markingStatus);
        checkArgument(isValidAssignmentName(assignmentName), MESSAGE_CONSTRAINTS);
        this.assignmentName = assignmentName.toUpperCase();
        this.markingStatus = markingStatus;
    }

    /**
     * Returns true if a given string is a valid assignment name.
     */
    public static boolean isValidAssignmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given string is a valid marking status.
     */
    public static boolean isValidMarkingStatus(String test) {
        return test.equals(ASSIGNMENT_MARKED)
                || test.equals(ASSIGNMENT_RECEIVED)
                || test.equals(ASSIGNMENT_NEW);
    }

    /**
     * Converts the status of the assignment to string.
     */
    public static String statusToString(Status status) {
        switch (status) {
        case MARKED:
            return ASSIGNMENT_MARKED;
        case RECEIVED:
            return ASSIGNMENT_RECEIVED;
        default:
            return ASSIGNMENT_NEW;
        }
    }

    /**
     * Converts a string to the assignment status.
     */
    public static Status stringToStatus(String statusString) throws IllegalValueException {
        statusString = statusString.toLowerCase();
        switch (statusString) {
        case ASSIGNMENT_MARKED:
            return Status.MARKED;
        case ASSIGNMENT_RECEIVED:
            return Status.RECEIVED;
        case ASSIGNMENT_NEW:
            return Status.NEW;
        default:
            throw new IllegalValueException(MESSAGE_STATUS_CONSTRAINTS);
        }
    }

    public String getString() {
        return assignmentName + ':' + statusToString(markingStatus);
    }

    public String getAssignmentString() {
        return statusToString(markingStatus);
    }

    public String getAssignmentName() {
        return assignmentName;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && assignmentName.equals(((Assignment) other).assignmentName)); // state check
                // no check for markingStatus to ensure only one assignment record per assignmentName
    }

    @Override
    public int hashCode() {
        return assignmentName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getString() + ']';
    }

}
