package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

//@@author parnikkapore-reused
// Cherry-picked from https://github.com/AY2223S1-CS2103T-T14-2/tp/pull/54/ to avoid duplicate work
// TODO: DELETE THIS NOTE WHILE MERGING

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
