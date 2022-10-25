package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests whether a {@code task}'s {@code Deadline} is before or on the day of the specified {@code Deadline}.
 */
public class TaskUntilDeadlinePredicate implements Predicate<Task> {
    private final Deadline deadline;

    public TaskUntilDeadlinePredicate(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns true if the task's deadline is before or on the day of the specified deadline.
     *
     * @param  task Task that will be checked for its deadline.
     * @return boolean indicating if task deadline is before or on the day of the specified deadline.
     */
    // TODO Implement for tags
    @Override
    public boolean test(Task task) {
        return task.deadlinesUpToAndIncluding(deadline);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskUntilDeadlinePredicate)) {
            return false;
        }

        TaskUntilDeadlinePredicate castedOther = (TaskUntilDeadlinePredicate) other;

        return deadline.equals(castedOther.deadline);
    }

}
