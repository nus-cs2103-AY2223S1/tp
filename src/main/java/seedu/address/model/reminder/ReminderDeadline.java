package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reminder's deadline in ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeslot(String)}
 */
public class ReminderDeadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be in HH:MM format, e.g 16:00";


    /*
     * Timeslot must be in HH:MM format.
     */
    public static final String VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final String deadline;

    /**
     * Constructs a {@code ReminderDeadline}.
     *
     * @param deadline A valid deadline.
     */
    public ReminderDeadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidTimeslot(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidTimeslot(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return deadline.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderDeadline // instanceof handles nulls
                && deadline.equals(((ReminderDeadline ) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
