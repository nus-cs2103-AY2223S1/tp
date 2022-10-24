package seedu.address.logic.parser.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.issue.Issue;

import java.util.List;

/**
 * Tests that an Issue object matches any of the keywords given.
 */
public class IssueContainsKeywordsPredicate {

    private final List<String> descriptionKeywords;
    private final List<String> statusKeywords;
    private final List<String> priorityKeywords;

    public IssueContainsKeywordsPredicate(List<String> descriptionKeywords,
                                          List<String> statusKeywords, List<String> priorityKeywords) {
        this.descriptionKeywords = descriptionKeywords;
        this.statusKeywords = statusKeywords;
        this.priorityKeywords = priorityKeywords;
    }

    public boolean testDescription(Issue issue) {
        return descriptionKeywords.isEmpty() ? true : descriptionKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(issue.getDescription().toString(), keyword));
    }

    public boolean testPriority(Issue issue) {
        return priorityKeywords.isEmpty() ? true : priorityKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(issue.getPriority().toString(), keyword));
    }

    public boolean testStatus(Issue issue) {
        return statusKeywords.isEmpty() ? true : statusKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(issue.getStatus().toString(), keyword));
    }

}
