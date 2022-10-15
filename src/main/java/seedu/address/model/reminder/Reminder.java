package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.datetime.Datetime;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {

    // Identity fields
    private final ReminderName name;

    //Data fields
    private final Datetime deadline;
    private final ReminderDescription details;

    /**
     * Every field must be present and not null.
     */
    public Reminder(ReminderName name, Datetime deadline, ReminderDescription details) {
        requireAllNonNull(name, deadline, details);
        this.name = name;
        this.deadline = deadline;
        this.details = details;
    }

    public ReminderName getName() {
        return name;
    }

    public Datetime getDeadline() {
        return deadline;
    }

    public ReminderDescription getDescription() {
        return details;
    }

    /**
     * Returns true if both reminders have the same name.
     * This defines a weaker notion of equality between two reminders.
     */
    public boolean isSameReminder(Reminder otherReminder) {
        if (otherReminder == this) {
            return true;
        }

        return otherReminder != null
                && otherReminder.getName().equals(getName());
    }

    /**
     * Returns true if both reminders have the same identity and data fields.
     * This defines a stronger notion of equality between two reminders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Reminder)) {
            return false;
        }

        Reminder otherReminder = (Reminder) other;
        return otherReminder.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, details);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Details: ")
                .append(getDescription());

        return builder.toString();
    }

}
