package seedu.address.logic.parser.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.project.Project;

/**
 * Tests that a Project object matches any of the keywords given.
 */
public class ProjectContainsKeywordsPredicate implements Predicate<Project> {

    private final List<String> nameKeywords;

    private final List<String> repositoryKeywords;

    /**
     * Constructs a ProjectContainsKeywordsPredicate object with the user inputs.
     * @param nameKeywords List of Strings representing keywords to search for in name
     * @param repositoryKeywords List of Strings representing keywords to search for in repository
     */
    public ProjectContainsKeywordsPredicate(List<String> nameKeywords, List<String> repositoryKeywords) {
        this.nameKeywords = nameKeywords;
        this.repositoryKeywords = repositoryKeywords;
    }

    /**
     * Checks if given name matches with any word in the name present.
     * @param namePresent String representing name present
     * @param nameGiven String representing name given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testName(String namePresent, String nameGiven) {
        return Arrays.stream(namePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(nameGiven, words));
    }

    /**
     * Checks if the project's name matches the name keyword being search for.
     * @param project Project whose name is being used to search the keyword in
     * @return boolean true if the name fulfills the search criteria and false otherwise
     */
    public boolean testName(Project project) {
        if (nameKeywords.isEmpty()) {
            return true;
        } else {
            return nameKeywords.stream().anyMatch(name -> testName(name, project.getProjectName().toString()));
        }
    }

    /**
     * Checks if given repository matches with any word in the repository present.
     * @param repoPresent String representing repository present
     * @param repoGiven String representing repository given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testRepository(String repoPresent, String repoGiven) {
        return Arrays.stream(repoPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(repoGiven, words));
    }

    /**
     * Checks if the project's repository matches the name keyword being search for.
     * @param project Project whose repository is being used to search the keyword in
     * @return boolean true if the repository fulfills the search criteria and false otherwise
     */
    public boolean testRepository(Project project) {
        if (repositoryKeywords.isEmpty()) {
            return true;
        } else {
            return repositoryKeywords.stream().anyMatch(name -> testRepository(name,
                    project.getRepository().toString()));
        }
    }

    @Override
    public boolean test(Project project) {
        return testName(project) && testRepository(project);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((ProjectContainsKeywordsPredicate) other).nameKeywords) //state checks
                && repositoryKeywords.equals(((ProjectContainsKeywordsPredicate) other).repositoryKeywords));
    }
}
