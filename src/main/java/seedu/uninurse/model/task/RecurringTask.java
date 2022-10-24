package seedu.uninurse.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Recurring Task for a Patient.
 */
public class RecurringTask extends Task {

    /**
     * Frequency is a enum which represents the different time frequencies the user can have for a recurring task.
     */
    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY;

        static int getDays(Frequency freq) {
            switch (freq) {
            case DAILY:
                return 1;
            case WEEKLY:
                return 7;
            case MONTHLY:
                return 30;
            default:
                return 0;
            }
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "TODO";

    public final Frequency recurrence;

    /**
     * Constructs a {@code RecurringTask} from the given description
     * dateAndTime and frequency.
     */
    public RecurringTask(String description, DateTime dateAndTime, Frequency freq) {
        super(description, dateAndTime);
        requireNonNull(freq);
        recurrence = freq;
    }

    /**
     * Returns whether the task date is past the current date.
     */
    public boolean pastTaskDate() {
        return super.getDateTime().isPastDate();
    }

    /**
     * Returns the next {@code RecurringTask} that's going to occur at the
     * RecurringTask's frequency
     */
    public RecurringTask getNextRecurringTask() {
        return new RecurringTask(super.getTaskDescription(),
                super.getDateTime().plusDays(Frequency.getDays(recurrence)), recurrence);
    }

    /**
     * Returns whether the given frequency string is valid.
     */
    public static boolean isValidFreq(String test) {
        try {
            Frequency.valueOf(test.toUpperCase());
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    /**
     * Returns {@code Frequency} based on the frequency string passed in.
     */
    public static Frequency parseFreq(String validFreq) {
        assert isValidFreq(validFreq);
        return Frequency.valueOf(validFreq.toUpperCase());
    }

    @Override
    public String toString() {
        return super.toString() + " " + recurrence;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecurringTask)) {
            return false;
        }

        RecurringTask o = (RecurringTask) other;

        return this.getTaskDescription().equals(o.getTaskDescription())
                && this.getDateTime().equals(o.getDateTime())
                && this.recurrence.equals(o.recurrence);
    }
}
