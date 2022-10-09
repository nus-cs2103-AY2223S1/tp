package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code task}'s {@code Description} and/or {@code Deadline}
 * matches any of the keywords given.
 */
public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    private final List<Description> descriptionKeywords;
    private final List<Deadline> deadlineKeywords;

    /**
     * Constructs an {@code TaskContainsKeywordsPredicate}.
     *
     * @param descriptionKeywords A list containing keywords for {@code Description}.
     * @param deadlineKeywords A list containing keywords for {@code Deadline}.
     */
    public TaskContainsKeywordsPredicate(List<Description> descriptionKeywords,
                                         List<Deadline> deadlineKeywords) {
        this.descriptionKeywords = descriptionKeywords;
        this.deadlineKeywords = deadlineKeywords;
    }

    @Override
    public boolean test(Task task) {
        return (descriptionKeywords.isEmpty() || descriptionKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(task.getDescription().toString(), keyword.toString())))
                && (deadlineKeywords.isEmpty() || deadlineKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(task.getDeadline().toString(), keyword.toString())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskContainsKeywordsPredicate)) {
            return false;
        }

        TaskContainsKeywordsPredicate castedOther = (TaskContainsKeywordsPredicate) other;

        return descriptionKeywords.equals(castedOther.descriptionKeywords)
                && deadlineKeywords.equals(castedOther.deadlineKeywords);
    }
}
