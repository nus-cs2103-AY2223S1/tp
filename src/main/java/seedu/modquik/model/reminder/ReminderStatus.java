package seedu.modquik.model.reminder;

/**
 * Represents a Reminder's completion status in ModQuik.
 */
public class ReminderStatus {
    private boolean isDone;

    public ReminderStatus(boolean status) {
        this.isDone = status;
    }

    public void mark(boolean status) {
        isDone = status;
    }

    public boolean getStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return isDone ? "COMPLETED" : "INCOMPLETE";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderStatus // instanceof handles nulls
                && isDone == ((ReminderStatus) other).isDone); // state check
    }

}
