package seedu.address.model.issue;

import static java.lang.Boolean.parseBoolean;

/**
 * Represents the various completion statuses of an issue.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Status should be completed or incomplete.";
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

    /**
     * Returns the ui representation of the status
     */
    public String uiRepresentation() {
        if (this.completed == true) {
            return "Status: Completed";
        } else {
            return "Status: Incomplete";
        }
    }

    /**
     * Returns the String representation of only the completion status.
     * @return String representing whether the issue is completed.
     */
    public String getCompletionStatus() {
        if (this.completed) {
            return "Completed";
        } else {
            return "Incomplete";
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.completed);
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Status)) {
            return false;
        }

        Status otherStatus = (Status) other;
        return otherStatus.completed == this.completed;

    }
}
