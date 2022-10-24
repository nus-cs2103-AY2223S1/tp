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

    private final List<String> descriptionKeywords;
    private final List<String> statusKeywords;
    private final List<String> priorityKeywords;

    private final List<String> projectNameKeywords;

    /**
     * Constructs an IssueContainsKeywordsPredicate object with the user inputs.
     * @param descriptionKeywords List of Strings representing keywords to search for in description
     * @param statusKeywords List of Strings representing keywords to search for in status
     * @param priorityKeywords List of Strings representing keywords to search for in priority
     * @param projectNameKeywords List of Strings representing keywords to search for in project name
     */
    public IssueContainsKeywordsPredicate(List<String> descriptionKeywords, List<String> statusKeywords,
                                          List<String> priorityKeywords, List<String> projectNameKeywords) {
        this.descriptionKeywords = descriptionKeywords;
        this.statusKeywords = statusKeywords;
        this.priorityKeywords = priorityKeywords;
        this.projectNameKeywords = projectNameKeywords;
    }

    /**
     * Checks if given description matches with any word in the description present.
     * @param descPresent String representing description present
     * @param descGiven String representing description given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testDescription(String descPresent, String descGiven) {
        return Arrays.stream(descPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(descGiven, words));
    }

    /**
     * Checks if the issue's description matches the description keyword being search for.
     * @param issue Issue whose description is being used to search the keyword in
     * @return true if the description fulfills the search criteria and false otherwise
     */
    public boolean testDescription(Issue issue) {
        if (descriptionKeywords.isEmpty()) {
            return true;
        } else {
            return descriptionKeywords.stream().anyMatch(
                    desc -> testDescription(desc, issue.getDescription().toString()));
        }
    }


    /**
     * Checks if given priority matches with any word in the priority present.
     * @param prPresent String representing priority present
     * @param prGiven String representing priority given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testPriority(String prPresent, String prGiven) {
        return Arrays.stream(prPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(prGiven, words));
    }

    /**
     * Checks if the issue's priority matches the priority keyword being search for.
     * @param issue Issue whose priority is being used to search the keyword in
     * @return true if the priority fulfills the search criteria and false otherwise
     */
    public boolean testPriority(Issue issue) {
        if (priorityKeywords.isEmpty()) {
            return true;
        } else {
            return priorityKeywords.stream().anyMatch(
                    pr -> testPriority(pr, issue.getPriority().toString()));
        }
    }

    /**
     * Checks if given status matches with any word in the priority present.
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
                    pr -> testStatus(pr, issue.getStatus().getCompletionStatus()));
        }
    }

    /**
     * Checks if given project name matches with any word in the priority present.
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
