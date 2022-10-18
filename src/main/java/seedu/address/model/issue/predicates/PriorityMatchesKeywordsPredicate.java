package seedu.address.model.issue.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.issue.Issue;


/**
 * Tests that a {@code Issue}'s {@code Priority} matches any of the keywords given.
 */
public class PriorityMatchesKeywordsPredicate implements Predicate<Issue> {
    private final List<String> keywords;

    public PriorityMatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Issue issue) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(issue.getPriority().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PriorityMatchesKeywordsPredicate) other).keywords)); // state check
    }
}
