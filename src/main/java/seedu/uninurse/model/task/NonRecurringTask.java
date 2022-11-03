package seedu.uninurse.model.task;

import java.util.ArrayList;
import java.util.List;

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

    public List<Task> updateTask() {
        return new ArrayList<>();
    }

    @Override
    public String getRecurrenceString() {
        return "One-Time";
    }
}
