package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code TaskDeadline} matches the date given.
 */
public class TaskDeadlineBeforeDatePredicate implements Predicate<Task> {
    private final TaskDeadline date;

    public TaskDeadlineBeforeDatePredicate(TaskDeadline date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return date.isAfter(task.getDeadline()) || date.equals(task.getDeadline());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadlineBeforeDatePredicate // instanceof handles nulls
                && date.equals(((TaskDeadlineBeforeDatePredicate) other).date)); // state check
    }

}
