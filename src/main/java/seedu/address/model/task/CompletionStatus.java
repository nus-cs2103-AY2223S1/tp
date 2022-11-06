package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;

/**
 * Represents the completion status of a task in the TaskList.
 */
public class CompletionStatus {
    /** Messages/Inputs representing the completion status */
    public static final String MESSAGE_TASK_COMPLETED = "Completed";
    public static final String MESSAGE_TASK_NOT_COMPLETED = "Not Completed";
    public static final String INPUT_TASK_COMPLETED = "complete";
    public static final String INPUT_TASK_NOT_COMPLETED = "incomplete";

    public static final String MESSAGE_CONSTRAINTS =
            "Completion status should be complete or incomplete";

    private final Boolean isCompleted;

    /**
     * Constructs an {@code CompletionStatus}.
     */
    public CompletionStatus() {
        this.isCompleted = false;
    }

    /**
     * Constructs an {@code CompletionStatus}.
     *
     * @param isCompleted The archival status of the task.
     */
    public CompletionStatus(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns true if completion status is completed.
     */
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Returns true if a given string is a valid completion status.
     */
    public static Boolean isValidCompletionStatus(String test) {
        return StringUtil.containsWordIgnoreCase(test, INPUT_TASK_COMPLETED)
                || StringUtil.containsWordIgnoreCase(test, INPUT_TASK_NOT_COMPLETED);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CompletionStatus)) {
            return false;
        }

        CompletionStatus otherCompletionStatus = (CompletionStatus) other;
        return otherCompletionStatus.getIsCompleted().equals(getIsCompleted());
    }

    @Override
    public int hashCode() {
        return isCompleted.hashCode();
    }

    @Override
    public String toString() {
        return isCompleted ? MESSAGE_TASK_COMPLETED : MESSAGE_TASK_NOT_COMPLETED;
    }
}
