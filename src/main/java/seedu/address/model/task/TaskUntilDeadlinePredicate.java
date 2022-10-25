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
     * Returns true if task contains any of the description or deadline keywords.
     * By default, empty keyword lists will return true.
     *
     * @param  task Task that will be checked for matching keywords.
     * @return boolean indicating if task contains supplied keywords.
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
