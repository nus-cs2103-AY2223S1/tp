package seedu.address.model.task;

/**
 * Represents the archival status of a task in the TaskList.
 */
public class ArchivalStatus {
    /** Messages representing the archival status */
    private static final String MESSAGE_TASK_ARCHIVED = "archived";
    private static final String MESSAGE_TASK_NOT_ARCHIVED = "not archived";

    public static final String MESSAGE_CONSTRAINTS =
            "Archival status should be " + MESSAGE_TASK_ARCHIVED + " or " + MESSAGE_TASK_NOT_ARCHIVED;

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

    /**
     * Returns true is archival status is archived.
     */
    public Boolean getIsArchived() {
        return isArchived;
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
