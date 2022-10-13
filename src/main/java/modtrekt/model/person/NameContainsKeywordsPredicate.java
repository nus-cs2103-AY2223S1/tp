package modtrekt.model.person;

import java.util.List;
import java.util.function.Predicate;

import modtrekt.commons.util.StringUtil;
import modtrekt.model.task.Task;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task t) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(t.getDescription().description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
