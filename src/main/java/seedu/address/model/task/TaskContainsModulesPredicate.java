package seedu.address.model.task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        if (other == this) {
            return true;
        } else if (other instanceof TaskContainsModulesPredicate) {
            Set<String> thisKeywordSet = new HashSet<>(keywords);
            Set<String> otherKeywordSet = new HashSet<>(((TaskContainsModulesPredicate) other).keywords);
            return thisKeywordSet.equals(otherKeywordSet);
        } else {
            return false;
        }
    }

}
