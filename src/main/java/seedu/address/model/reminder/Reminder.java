package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Reminder in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {

    // Identity fields
    private final ReminderName name;

    // Data fields
    private final ReminderDeadline deadline;
    private final ReminderPriority priority;
    private final ReminderDescription description;
    private ReminderStatus isDone;

    /**
     * Every field must be present and not null.
     */
    public Reminder(ReminderName name, ReminderDeadline deadline, ReminderPriority priority,
                    ReminderDescription description) {
        requireAllNonNull(name, deadline, priority);
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.description = description;
        this.isDone = new ReminderStatus(false);
    }

    /**
     * Every field must be present and not null.
     */
    public Reminder(ReminderName name, ReminderDeadline deadline, ReminderPriority priority,
                    ReminderDescription description, ReminderStatus status) {
        requireAllNonNull(name, deadline, priority);
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.description = description;
        this.isDone = status;
    }

    public void setStatus(boolean status) {
        isDone.mark(status);
    }

    public ReminderName getName() {
        return name;
    }

    public ReminderDeadline getDeadline() {
        return deadline;
    }

    public ReminderPriority getPriority() {
        return priority;
    }
    
    public ReminderDescription getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone.getStatus();
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
        return Objects.hash(name, deadline, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Details: ")
                .append(getDescription());

        return builder.toString();
    }

}
