package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

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

    /**
     * Returns true if task contains any of the description or deadline keywords.
     * By default, empty keyword lists will return true.
     *
     * @param  task Task that will be checked for matching keywords.
     * @return boolean indicating if task contains supplied keywords.
     */
    @Override
    public boolean test(Task task) {
        return task.containsKeywords(descriptionKeywords, deadlineKeywords);
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
