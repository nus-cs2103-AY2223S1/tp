package seedu.address.model.task;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code CategoryType} and {@code TaskDeadline}
 * matches any of the keywords and date given respectively.
 */
public class TaskCategoryAndDeadlinePredicate implements Predicate<Task> {
    private final Optional<TaskCategory> category;
    private final Optional<TaskDate> date;
    private final int option;

    /**
     * Constructor method for {@code TaskCategoryAndDeadlinePredicate} class.
     */
    public TaskCategoryAndDeadlinePredicate(Optional<TaskCategory> category, Optional<TaskDate> date) {
        this.category = category;
        this.date = date;

        if (category.isPresent() && date.isPresent()) {
            option = 0;
        } else if (category.isPresent()) {
            option = 1;
        } else {
            option = 2;
        }
    }

    @Override
    public boolean test(Task task) {
        boolean result = false;

        if (option == 0) {
            result = task.getCategory().equals(category.get())
                    && (date.get().isAfter(task.getDeadline().getDeadline())
                    || date.get().getDate().equals(task.getDeadline().getDeadline()));
        }

        if (option == 1) {
            result = task.getCategory().equals(category.get());
        }

        if (option == 2) {
            result = date.get().isAfter(task.getDeadline().getDeadline())
                    || date.get().getDate().equals(task.getDeadline().getDeadline());
        }

        return result;
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

    public TaskCategory getCategory() {
        if (this.category.isPresent()) {
            return this.category.get();
        } else {
            return null;
        }
    }

    public TaskDate getDate() {
        if (this.date.isPresent()) {
            return this.date.get();
        } else {
            return null;
        }
    }
}
