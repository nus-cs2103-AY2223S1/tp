package seedu.address.logic.parser.predicates;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.issue.Issue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an Issue object matches any of the keywords given.
 */
public class IssueContainsKeywordsPredicate implements Predicate<Issue> {

    private final List<String> descriptionKeywords;
    private final List<String> statusKeywords;
    private final List<String> priorityKeywords;

    private final List<String> projectNameKeywords;

    public IssueContainsKeywordsPredicate(List<String> descriptionKeywords, List<String> statusKeywords,
                                          List<String> priorityKeywords, List<String> projectNameKeywords) {
        this.descriptionKeywords = descriptionKeywords;
        this.statusKeywords = statusKeywords;
        this.priorityKeywords = priorityKeywords;
        this.projectNameKeywords = projectNameKeywords;
    }

    public boolean testDescription(String descPresent, String descGiven) {
        return Arrays.stream(descPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(descGiven, words));
    }

    public boolean testPriority(String prPresent, String prGiven) {
        return Arrays.stream(prPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(prGiven, words));
    }

    public boolean testStatus(String stPresent, String stGiven) {
        return Arrays.stream(stPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(stGiven, words));
    }

    public boolean testProjectName(String projPresent, String projGiven) {
        return Arrays.stream(projPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(projGiven, words));
    }

    public boolean testDescription(Issue issue) {
        if (descriptionKeywords.isEmpty()) {
            return true;
        } else {
            return descriptionKeywords.stream().anyMatch(
                    desc -> testDescription(desc, issue.getDescription().toString()));
        }
    }

    public boolean testPriority(Issue issue) {
        if (priorityKeywords.isEmpty()) {
            return true;
        } else {
            return priorityKeywords.stream().anyMatch(
                    pr -> testPriority(pr, issue.getPriority().toString()));
        }
    }

    public boolean testStatus(Issue issue) {
        if (statusKeywords.isEmpty()) {
            return true;
        } else {
            return statusKeywords.stream().anyMatch(
                    pr -> testStatus(pr, issue.getStatus().toString()));
        }
    }

    public boolean testProjectName(Issue issue) {
        if (projectNameKeywords.isEmpty()) {
            return true;
        } else {
            return projectNameKeywords.stream().anyMatch(
                    pr -> testStatus(pr, issue.getProject().getProjectName().toString()));
        }
    }

    @Override
    public boolean test(Issue issue) {
        return testDescription(issue) && testPriority(issue) && testStatus(issue) && testProjectName(issue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IssueContainsKeywordsPredicate // instanceof handles nulls
                && descriptionKeywords.equals(((IssueContainsKeywordsPredicate) other).descriptionKeywords)
                && statusKeywords.equals(((IssueContainsKeywordsPredicate) other).statusKeywords) //state checks
                && priorityKeywords.equals(((IssueContainsKeywordsPredicate) other).priorityKeywords)
                && projectNameKeywords.equals(((IssueContainsKeywordsPredicate) other).projectNameKeywords));
    }

}
