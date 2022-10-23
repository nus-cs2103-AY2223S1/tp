package seedu.address.model.reminder;

/**
 * Represents a Reminder's description in ModQuik.
 * Guarantees: immutable;
 */
public class ReminderDescription {
    public final String description;

    public ReminderDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description.toString();
    }
}
