package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name or Tag} matches any of the keywords given.
 */
public class NameOrTagContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public NameOrTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsIgnoreCase(task.getName().fullName, keyword)
                        || task.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameOrTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameOrTagContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        if (this.keywords.size() != 0) {
            return "Names or tags containing " + this.keywords;
        } else {
            return "Names or tags containing ''";
        }
    }


}
