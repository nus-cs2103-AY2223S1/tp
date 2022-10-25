package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reminder's priority in the ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class ReminderPriority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority should only be either HIGH, MEDIUM or LOW";

    /*
     * Priority should only be either HIGH, MEDIUM or LOW, case-insensitive.
     */
    public static final String VALIDATION_REGEX = "(HIGH)|(MEDIUM)|(LOW)";

    public final String priority;

    /**
     * Constructs a {@code ReminderPriority}.
     *
     * @param priority A valid name.
     */
    public ReminderPriority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.priority = priority;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return priority;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderPriority // instanceof handles nulls
                && priority.equals(((ReminderPriority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }

}
