package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s deadline is after the specified date.
 */
public class DeadlineIsAfterPredicate implements Predicate<Task> {
    private final Deadline deadline;

    /**
     * Constructs a DeadlineIsBeforePredicate.
     * @param deadline the date to use for comparison
     */
    public DeadlineIsAfterPredicate(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean test(Task task) {
        return task.getDeadline().isAfter(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineIsAfterPredicate // instanceof handles nulls
                && deadline.equals(((DeadlineIsAfterPredicate) other).deadline)); // state check
    }
}
