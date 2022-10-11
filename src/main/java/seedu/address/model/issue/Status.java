package seedu.address.model.issue;

import static java.lang.Boolean.parseBoolean;

/**
 * Represents the various completion statuses of an issue.
 */
public class Status {

    /**
     * Represents and empty Issue status.
     */
    public static class EmptyStatus extends Status {
        public static final Status EMPTY_STATUS = new EmptyStatus();

        private EmptyStatus() {
            super(false);
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }

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

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String status) {
        if (status.toUpperCase().equals("FALSE")) {
            return !parseBoolean(status);
        } else {
            return parseBoolean(status);
        }
    }

    public boolean getStatus() {
        return this.completed;
    }

    public void setStatus(boolean completionStatus) {
        this.completed = completionStatus;
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.completed);
    }
}
