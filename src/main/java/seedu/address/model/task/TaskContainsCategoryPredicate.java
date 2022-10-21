package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code CategoryType} matches any of the keywords given.
 */
public class TaskContainsCategoryPredicate implements Predicate<Task> {
    private final TaskCategory category;

    public TaskContainsCategoryPredicate(TaskCategory category) {
        this.category = category;
    }

    @Override
    public boolean test(Task task) {
        return task.getCategory().equals(category);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsCategoryPredicate // instanceof handles nulls
                && category.equals(((TaskContainsCategoryPredicate) other).category)); // state check
    }

}
