package seedu.uninurse.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Recurring Task for a Patient.
 */
public class RecurringTasks extends Task {

    /**
     * TODO
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
     * TODO
     */
    public RecurringTasks(String description, DateTime dateAndTime, Frequency freq) {
        super(description, dateAndTime);
        requireNonNull(freq);
        recurrence = freq;
    }

    public boolean pastTaskDate() {
        return super.dateTime.isPastDate();
    }

    public RecurringTasks getNextRecurringTask() {
        return new RecurringTasks(super.taskDescription,
                super.dateTime.plusDays(Frequency.getDays(recurrence)), recurrence);
    }

    /**
     * TODO
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
     * TODO
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

        if (!(other instanceof RecurringTasks)) {
            return false;
        }

        RecurringTasks o = (RecurringTasks) other;

        return this.taskDescription.equals(o.taskDescription)
                && this.dateTime.equals(o.dateTime)
                && this.recurrence.equals(o.recurrence);
    }
}
