package seedu.address.model.issue;

import static java.lang.Boolean.parseBoolean;

/**
 * Represents the various completion statuses of an issue.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "STATUS NOT IMPLEMENTED";

    private boolean completed;

    /**
     * Constructs an issue completion Status.
     *
     * @param completionStatus true/false to indicate if issue is completed.
     */
    public Status(boolean completionStatus) {
        this.completed = completionStatus;
    }

    public static boolean isValidStatus(String status) {
        if (status.toUpperCase().equals("FALSE")) {
            return !parseBoolean(status);
        } else {
            return parseBoolean(status);
        }
    }

    /**
     * Represents and empty Issue status.
     */
    private static class EmptyStatus extends Status {
        private static final Status EMPTY_STATUS = new EmptyStatus();

        private EmptyStatus() {
            super(false);
        }
    }

    public boolean getStatus() {
        return this.completed;
    }

    public void setStatus(boolean completionStatus) {
        this.completed = completionStatus;
    }

    @Override
    public String toString() {
        return String.valueOf(this.completed);
    }
}
