package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Status class represents the status of the task - whether it is complete.
 */
public class Status {

    private static final String COMPLETE_STRING = "complete";
    private static final String INCOMPLETE_STRING = "incomplete";

    public static final Status COMPLETE = new Status(COMPLETE_STRING);
    public static final Status INCOMPLETE = new Status(INCOMPLETE_STRING);

    public static final String STATUS_CONSTRAINTS =
        "The status of the task should be " + COMPLETE + " or " + INCOMPLETE;

    public final String status;

    private Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), STATUS_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Constructs a {@code Status}.
     * It is either complete or incomplete.
     *
     * @param status A valid status.
     */
    public static Status of(String status) {
        if (status.equals(COMPLETE_STRING)) {
            return COMPLETE;
        } else {
            return INCOMPLETE;
        }
    }

    public static boolean isValidStatus(String status) {
        return status.equals(COMPLETE_STRING) || status.equals(INCOMPLETE_STRING);
    }

    public boolean isComplete() {
        return status.equals(COMPLETE_STRING);
    }

    @Override
    public boolean equals(Object otherStatus) {
        return otherStatus == this || (otherStatus instanceof Status
            && status.equalsIgnoreCase((((Status) otherStatus).status)));
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

