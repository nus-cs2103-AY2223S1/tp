package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class TaskDescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
