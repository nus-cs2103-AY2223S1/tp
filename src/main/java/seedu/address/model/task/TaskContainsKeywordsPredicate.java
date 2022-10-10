package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskContainsKeywordsPredicate) other).keywords)); // state check
    }

}
