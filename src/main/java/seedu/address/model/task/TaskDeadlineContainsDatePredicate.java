package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code TaskDeadline} matches the date given.
 */
public class TaskDeadlineContainsDatePredicate implements Predicate<Task> {
    private final TaskDeadline date;

    public TaskDeadlineContainsDatePredicate(TaskDeadline date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return date.equals(task.getDeadline());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadlineContainsDatePredicate // instanceof handles nulls
                && date.equals(((TaskDeadlineContainsDatePredicate) other).date)); // state check
    }

}
