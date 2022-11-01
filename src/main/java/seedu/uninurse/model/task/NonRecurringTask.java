package seedu.uninurse.model.task;

/**
 * TODO JavaDoc for NonRecurringTask class
 */
public class NonRecurringTask extends Task {

    /**
     * Constructs a {@code Task} with the given {@code description}.
     */
    public NonRecurringTask(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Task} with the given {@code description} and {@code dateAndTime}.
     */
    public NonRecurringTask(String description, DateTime dateAndTime) {
        super(description, dateAndTime);
    }

    @Override
    public String getRecurrenceString() {
        return "One-Time";
    }
}
