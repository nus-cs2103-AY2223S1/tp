package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code TaskDeadline} matches the date given.
 */
public class TaskDeadlineBeforeDatePredicate implements Predicate<Task> {
    private final TaskDate date;

    /**
     * Constructor method for {@code TaskDeadlineBeforeDatePredicate} class.
     * @param date the TaskDeadline to be used as comparison
     */
    public TaskDeadlineBeforeDatePredicate(TaskDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return date.isAfter(task.getDeadline().getDeadline())
                || date.getDate().equals(task.getDeadline().getDeadline());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadlineBeforeDatePredicate // instanceof handles nulls
                && date.equals(((TaskDeadlineBeforeDatePredicate) other).date)); // state check
    }

}
