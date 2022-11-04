package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * TaskStatus class represents the status of the task - whether it is complete.
 */
public class TaskStatus {

    private static final String COMPLETE_STRING = "complete";
    private static final String INCOMPLETE_STRING = "incomplete";

    public static final TaskStatus COMPLETE = new TaskStatus(COMPLETE_STRING);
    public static final TaskStatus INCOMPLETE = new TaskStatus(INCOMPLETE_STRING);

    public static final String STATUS_CONSTRAINTS =
        "The status of the task should be " + COMPLETE + " or " + INCOMPLETE;

    public final String status;

    private TaskStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), STATUS_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Constructs a {@code TaskStatus}.
     * It is either complete or incomplete.
     *
     * @param status A valid status.
     */
    public static TaskStatus of(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), STATUS_CONSTRAINTS);
        if (status.equalsIgnoreCase(COMPLETE_STRING)) {
            return COMPLETE;
        } else {
            return INCOMPLETE;
        }
    }

    /**
     * Returns true if the given string is "complete" or "incomplete",
     * false otherwise.
     * This comparison is not case-sensitive.
     *
     * @param status string representation of a status.
     * @return true if the given string is a valid status, false otherwise.
     */
    public static boolean isValidStatus(String status) {
        return status.equalsIgnoreCase(COMPLETE_STRING) || status.equalsIgnoreCase(INCOMPLETE_STRING);
    }

    /**
     * Returns true if task status is "complete",
     * false otherwise.
     *
     * @return true if task status is "complete".
     */
    public boolean isComplete() {
        return this == COMPLETE;
    }

    @Override
    public boolean equals(Object otherStatus) {
        return otherStatus == this || (otherStatus instanceof TaskStatus
            && status.equalsIgnoreCase((((TaskStatus) otherStatus).status)));
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    @Override
    public String toString() {
        return status;
    }
}

