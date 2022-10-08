package seedu.address.model.issue;

/**
 * Represents the various completion statuses of an issue.
 */
public class Status {

    /**
     * Represents and empty Issue status.
     */
    private static class EmptyStatus extends Status {
        private static final Status EMPTY_STATUS = new EmptyStatus();

        private EmptyStatus() {
            super(false);
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
