package seedu.address.model.issue.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.issue.Issue;


/**
 * Tests that a {@code Issue}'s {@code Urgency} matches any of the keywords given.
 */
public class UrgencyMatchesKeywordsPredicate implements Predicate<Issue> {
    private final List<String> keywords;

    public UrgencyMatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Issue issue) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(issue.getUrgency().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UrgencyMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((UrgencyMatchesKeywordsPredicate) other).keywords)); // state check
    }
}
