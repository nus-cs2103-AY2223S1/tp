package seedu.address.logic.parser.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.issue.Issue;


/**
 * Tests that an Issue object matches any of the keywords given.
 */
public class IssueContainsKeywordsPredicate implements Predicate<Issue> {

    private final List<String> titleKeywords;
    private final List<String> statusKeywords;
    private final List<String> urgencyKeywords;

    private final List<String> projectNameKeywords;

    /**
     * Constructs an IssueContainsKeywordsPredicate object with the user inputs.
     * @param titleKeywords List of Strings representing keywords to search for in title
     * @param statusKeywords List of Strings representing keywords to search for in status
     * @param urgencyKeywords List of Strings representing keywords to search for in urgency
     * @param projectNameKeywords List of Strings representing keywords to search for in project name
     */
    public IssueContainsKeywordsPredicate(List<String> titleKeywords, List<String> statusKeywords,
                                          List<String> urgencyKeywords, List<String> projectNameKeywords) {
        this.titleKeywords = titleKeywords;
        this.statusKeywords = statusKeywords;
        this.urgencyKeywords = urgencyKeywords;
        this.projectNameKeywords = projectNameKeywords;
    }

    /**
     * Checks if given title matches with any word in the title present.
     * @param tiPresent String representing title present
     * @param tiGiven String representing title given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testTitle(String tiPresent, String tiGiven) {
        return Arrays.stream(tiPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(tiGiven, words));
    }

    /**
     * Checks if the issue's title matches the title keyword being search for.
     * @param issue Issue whose title is being used to search the keyword in
     * @return true if the title fulfills the search criteria and false otherwise
     */
    public boolean testTitle(Issue issue) {
        if (titleKeywords.isEmpty()) {
            return true;
        } else {
            return titleKeywords.stream().anyMatch(
                    ti -> testTitle(ti, issue.getTitle().toString()));
        }
    }


    /**
     * Checks if given urgency matches with any word in the urgency present.
     * @param urPresent String representing urgency present
     * @param urGiven String representing urgency given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testUrgency(String urPresent, String urGiven) {
        return Arrays.stream(urPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(urGiven, words));
    }

    /**
     * Checks if the issue's urgency matches the urgency keyword being search for.
     * @param issue Issue whose urgency is being used to search the keyword in
     * @return true if the urgency fulfills the search criteria and false otherwise
     */
    public boolean testUrgency(Issue issue) {
        if (urgencyKeywords.isEmpty()) {
            return true;
        } else {
            return urgencyKeywords.stream().anyMatch(
                    ur -> testUrgency(ur, issue.getUrgency().toString()));
        }
    }

    /**
     * Checks if given status matches with any word in the status present.
     * @param stPresent String representing status present
     * @param stGiven String representing status given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testStatus(String stPresent, String stGiven) {
        return Arrays.stream(stPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(stGiven, words));
    }

    /**
     * Checks if the issue's status matches the status keyword being search for.
     * @param issue Issue whose status is being used to search the keyword in
     * @return true if the status fulfills the search criteria and false otherwise
     */
    public boolean testStatus(Issue issue) {
        if (statusKeywords.isEmpty()) {
            return true;
        } else {
            return statusKeywords.stream().anyMatch(
                    st -> testStatus(st, issue.getStatus().getCompletionStatus()));
        }
    }

    /**
     * Checks if given project name matches with any word in the project name present.
     * @param projPresent String representing project name present
     * @param projGiven String representing project name given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testProjectName(String projPresent, String projGiven) {
        return Arrays.stream(projPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(projGiven, words));
    }

    /**
     * Checks if the issue's project's name matches the project name keyword being search for.
     * @param issue Issue whose project name is being used to search the keyword in
     * @return true if the project name fulfills the search criteria and false otherwise
     */
    public boolean testProjectName(Issue issue) {
        if (projectNameKeywords.isEmpty()) {
            return true;
        } else {
            return projectNameKeywords.stream().anyMatch(
                    pr -> testProjectName(pr, issue.getProject().getProjectName().toString()));
        }
    }

    @Override
    public boolean test(Issue issue) {
            return testTitle(issue) && testUrgency(issue) && testStatus(issue) && testProjectName(issue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IssueContainsKeywordsPredicate // instanceof handles nulls
                && titleKeywords.equals(((IssueContainsKeywordsPredicate) other).titleKeywords)
                && statusKeywords.equals(((IssueContainsKeywordsPredicate) other).statusKeywords) //state checks
                && urgencyKeywords.equals(((IssueContainsKeywordsPredicate) other).urgencyKeywords)
                && projectNameKeywords.equals(((IssueContainsKeywordsPredicate) other).projectNameKeywords));
    }

}
