package seedu.address.model.issue.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.issue.Issue;

import java.util.List;
import java.util.function.Predicate;

public class StatusMatchesKeywordsPredicate implements Predicate<Issue> {
    private final List<String> keywords;

    public StatusMatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Issue issue) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(issue.getStatus().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StatusMatchesKeywordsPredicate) other).keywords)); // state check
    }
}
