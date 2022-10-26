package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code module} matches the module given.
 */
public class TaskContainsModulesPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskContainsModulesPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> task.getModule().moduleName.toUpperCase()
                        .contains(keyword.toUpperCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsModulesPredicate // instanceof handles nulls
                && keywords.equals(((TaskContainsModulesPredicate) other).keywords)); // state check
    }

}
