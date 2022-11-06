package seedu.modquik.model.reminder;

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderDescription // instanceof handles nulls
                && description.equals(((ReminderDescription) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
