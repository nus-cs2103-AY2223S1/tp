package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code CategoryType} and {@code TaskDeadline}
 * matches any of the keywords and date given respectively.
 */
public class TaskCategoryAndDeadlinePredicate implements Predicate<Task> {
    private final TaskCategory category;
    private final TaskDeadline date;

    /**
     * Constructor method for {@code TaskCategoryAndDeadlinePredicate} class.
     * @param category the TaskCategory to be used as comparison
     * @param date the TaskDeadline to be used as comparison
     */
    public TaskCategoryAndDeadlinePredicate(TaskCategory category, TaskDeadline date) {
        this.category = category;
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return task.getCategory().equals(category)
                && (date.isAfter(task.getDeadline()) || date.equals(task.getDeadline()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCategoryAndDeadlinePredicate)) {
            return false;
        }

        // state check
        TaskCategoryAndDeadlinePredicate t = (TaskCategoryAndDeadlinePredicate) other;
        return category.equals(t.category) && date.equals(t.date);
    }
}
