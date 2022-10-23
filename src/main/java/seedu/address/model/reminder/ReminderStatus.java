package seedu.address.model.reminder;

/**
 * Represents a Reminder's status in ModQuik.
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
        if (isDone) {
            return "Reminder is done.";
        } else {
            return "Reminder is not done yet.";
        }
    }
}
