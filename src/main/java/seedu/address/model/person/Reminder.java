package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Reminder in the address book.
 */
public class Reminder {
    public final String task;
    public final String date;

    public Reminder(String reminder, String date) {
        requireNonNull(reminder, date);
        this.task = reminder;
        this.date = date;
    }

    public Reminder(String reminder) {
        requireNonNull(reminder);
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

