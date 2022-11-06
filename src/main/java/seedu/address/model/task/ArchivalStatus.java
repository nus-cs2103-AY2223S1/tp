package seedu.address.model.task;

/**
 * Represents the archival status of a task in the TaskList.
 */
public class ArchivalStatus {
    /** Messages representing the archival status */
    private static final String MESSAGE_TASK_ARCHIVED = "archived";
    private static final String MESSAGE_TASK_NOT_ARCHIVED = "not archived";

    private final Boolean isArchived;

    /**
     * Constructs an {@code ArchivalStatus}.
     */
    public ArchivalStatus() {
        this.isArchived = false;
    }

    /**
     * Constructs an {@code ArchivalStatus}.
     *
     * @param isArchived The archival status of the task.
     */
    public ArchivalStatus(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    public Boolean getIsArchived() {
        return isArchived;
    }

    public static Boolean isValidArchivalStatus(String test) {
        return test.equals(MESSAGE_TASK_ARCHIVED) || test.equals(MESSAGE_TASK_NOT_ARCHIVED);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ArchivalStatus)) {
            return false;
        }

        ArchivalStatus otherArchivalStatus = (ArchivalStatus) other;
        return otherArchivalStatus.getIsArchived().equals(getIsArchived());
    }

    @Override
    public int hashCode() {
        return isArchived.hashCode();
    }

    @Override
    public String toString() {
        return isArchived ? MESSAGE_TASK_ARCHIVED : MESSAGE_TASK_NOT_ARCHIVED;
    }
}
