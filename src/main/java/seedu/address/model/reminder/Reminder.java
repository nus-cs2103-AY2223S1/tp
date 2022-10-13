package seedu.address.model.reminder;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {
    
    // Identity fields
    private final ReminderName name;
    
    //Data fields
    private final ReminderDeadline deadline;
    private final ReminderDescription details;

    /**
     * Every field must be present and not null.
     */
    public Reminder(ReminderName name, ReminderDeadline deadline, ReminderDescription details) {
        requireAllNonNull(name, deadline, details);
        this.name = name;
        this.deadline = deadline;
        this.details = details;
    }

    public ReminderName getName() {
        return name;
    }

    public ReminderDeadline getDeadline() {
        return deadline;
    }

    public ReminderDescription getDetails() {
        return details;
    }

    /**
     * Returns true if both tutorials have the same identity and data fields.
     * This defines a stronger notion of equality between two tutorials.
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
                .append(getDetails());
        
        return builder.toString();
    }

    
}
