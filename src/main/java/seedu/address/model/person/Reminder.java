package seedu.address.model.person;

/**
 * Represents a Reminder in the address book.
 */
public class Reminder {
    public final String task;
    public final String date;

    /**
     * Constructs an {@code Reminder}.
     *
     * @param reminder A valid reminder.
     * @param date A valid date to associated to the reminder.
     */
    public Reminder(String reminder, String date) {
        this.task = reminder;
        this.date = date;
    }

    /**
     * Constructs an {@code Reminder}.
     *
     * @param reminder A valid reminder.
     */
    public Reminder(String reminder) {
        this.task = reminder;
        this.date = "";
    }

    @Override
    public String toString() {
        return this.task + " by: " + this.date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reminder // instanceof handles nulls
                && task.equals(((Reminder) other).task) && date.equals(((Reminder) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }
}

