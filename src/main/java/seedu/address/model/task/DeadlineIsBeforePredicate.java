package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s deadline is before the specified date.
 */
public class DeadlineIsBeforePredicate implements Predicate<Task> {
    private final Deadline deadline;

    /**
     * Constructs a DeadlineIsBeforePredicate.
     * @param deadline the deadline to use for comparison
     */
    public DeadlineIsBeforePredicate(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean test(Task task) {
        return task.getDeadline().isBefore(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineIsBeforePredicate // instanceof handles nulls
                && deadline.equals(((DeadlineIsBeforePredicate) other).deadline)); // state check
    }
}
