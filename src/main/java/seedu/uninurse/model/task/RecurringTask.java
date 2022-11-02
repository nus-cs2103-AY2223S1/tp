package seedu.uninurse.model.task;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Recurring Task for a Patient.
 */
public class RecurringTask extends Task {

    // TODO MESSAGE_CONSTRAINTS
    public static final String MESSAGE_CONSTRAINTS = "Please enter a valid recurrence time period and time amount"
            + " e.g 3 weeks or 2 months";

    private final Recurrence recurrence;
    private final int frequency;

    /**
     * Constructs a {@code RecurringTask} from the given description
     * dateAndTime and frequency.
     */
    public RecurringTask(String description, DateTime dateAndTime, Recurrence recur, int freq) {
        super(description, dateAndTime);
        requireNonNull(recur);
        recurrence = recur;
        frequency = freq;
    }

    /**
     * Returns the next {@code RecurringTask} that's going to occur at the
     * RecurringTask's frequency
     */
    public RecurringTask getNextRecurringTask() {
        return new RecurringTask(super.getTaskDescription(),
                super.getDateTime().plusDuration(recurrence, frequency), recurrence, frequency);
    }

    /**
     * Returns whether the given frequency string is valid.
     */
    public static boolean isValidRecurAndFreq(String test) {
        String[] recurAndFreq = test.trim().split(" ");

        if (recurAndFreq.length != 2) {
            return false;
        }

        int freq;
        try {
            freq = parseInt(recurAndFreq[0].trim(), 10);
        } catch (NumberFormatException nfe) {
            return false;
        }

        if (freq <= 0) {
            return false;
        }

        String recur = recurAndFreq[1].trim();

        try {
            Recurrence.valueOf(recur.toUpperCase());
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    /**
     * Returns {@code RecurringTask} by parsing the validRecurFreq string and then
     * combining them with task description and datetime to construct a {@code RecurringTask}.
     */
    public static RecurringTask parseRecurringTask(String description, DateTime dateTime, String validRecurFreq) {
        assert isValidRecurAndFreq(validRecurFreq);
        String[] recurAndFreq = validRecurFreq.trim().split(" ");
        int freq = parseInt(recurAndFreq[0].trim(), 10);
        Recurrence recur = Recurrence.valueOf(recurAndFreq[1].trim().toUpperCase());

        return new RecurringTask(description, dateTime, recur, freq);
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public List<Task> updateTask(List<Task> taskList) {
        ArrayList<Task> updatedTasks = new ArrayList<>(taskList);
        RecurringTask nextRecurringTask = this;

        while (nextRecurringTask.passedTaskDate()) {
            if (!updatedTasks.contains(nextRecurringTask)) {
                updatedTasks.add(nextRecurringTask);
            }
            nextRecurringTask = nextRecurringTask.getNextRecurringTask();
        }

        return updatedTasks;
    }

    @Override
    public String getRecurrenceString() {
        return String.format(" Every %s %s", getFrequency(),
                getRecurrence().toString().toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + " every " + frequency + " " + recurrence;
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
                && this.recurrence.equals(o.recurrence)
                && this.frequency == (o.frequency);
    }
}
