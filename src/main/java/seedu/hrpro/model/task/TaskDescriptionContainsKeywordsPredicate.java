package seedu.hrpro.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.hrpro.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code TaskDescription} matches any of the keywords given.
 */
public class TaskDescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskDescriptionContainsKeywordsPredicate(List<String> keyWords) {
        this.keywords = keyWords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsNameIgnoreCase(
                        task.getTaskDescription().taskDescription, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
