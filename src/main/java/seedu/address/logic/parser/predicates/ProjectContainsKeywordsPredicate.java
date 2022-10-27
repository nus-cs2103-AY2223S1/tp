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
    private final List<String> clientNameKeywords;
    private final List<String> clientIdKeywords;
    private final List<String> projectIdKeywords;

    /**
     * Constructs a ProjectContainsKeywordsPredicate object with the user inputs.
     * @param nameKeywords List of Strings representing keywords to search for in name
     * @param repositoryKeywords List of Strings representing keywords to search for in repository
     * @param clientNameKeywords List of Strings representing keywords to search for in project's client's name
     * @param clientIdKeywords List of Strings representing keywords to search for in project's client's Id
     * @param projectIdKeywords List of Strings representing keywords to search for in project Id
     */
    public ProjectContainsKeywordsPredicate(List<String> nameKeywords, List<String> repositoryKeywords,
                                            List<String> clientNameKeywords, List<String> clientIdKeywords,
                                            List<String> projectIdKeywords) {
        this.nameKeywords = nameKeywords;
        this.repositoryKeywords = repositoryKeywords;
        this.clientNameKeywords = clientNameKeywords;
        this.clientIdKeywords = clientIdKeywords;
        this.projectIdKeywords = projectIdKeywords;
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
     * Checks if given client name matches with any word in the name present.
     * @param namePresent String representing client name present
     * @param nameGiven String representing client name given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testClientName(String namePresent, String nameGiven) {
        return Arrays.stream(namePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(nameGiven, words));
    }

    /**
     * Checks if the project's client's name matches the name keyword being search for.
     * @param project Project whose client's name is being used to search the keyword in
     * @return boolean true if the name fulfills the search criteria and false otherwise
     */
    public boolean testClientName(Project project) {
        if (clientNameKeywords.isEmpty()) {
            return true;
        } else {
            return clientNameKeywords.stream().anyMatch(name -> testClientName(name,
                    project.getClient().getClientName().toString()));
        }
    }

    /**
     * Checks if given client id matches with any word in the client id present.
     * @param idPresent String representing client id present
     * @param idGiven String representing client id given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testClientId(String idPresent, String idGiven) {
        return Arrays.stream(idPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(idGiven, words));
    }

    /**
     * Checks if the project's client's id matches the id keyword being search for.
     * @param project Project whose client's id is being used to search the keyword in
     * @return boolean true if the id fulfills the search criteria and false otherwise
     */
    public boolean testClientId(Project project) {
        if (clientIdKeywords.isEmpty()) {
            return true;
        } else {
            return clientIdKeywords.stream().anyMatch(id -> testClientId(id,
                    project.getClient().getClientId().toString()));
        }
    }

    /**
     * Checks if the project's id matches the id keyword being search for.
     * @param project Project whose id is being used to search the keyword in
     * @return boolean true if the id fulfills the search criteria and false otherwise
     */
    public boolean testProjectId(Project project) {
        if (projectIdKeywords.isEmpty()) {
            return true;
        } else {
            return projectIdKeywords.stream().anyMatch(id -> testProjectId(id,
                    project.getProjectId().toString()));
        }
    }

    /**
     * Checks if given id matches with any word in the id present.
     * @param idPresent String representing id present
     * @param idGiven String representing id given (keyword to search for)
     * @return boolean true if at least one word matches with the keyword and false otherwise
     */
    public boolean testProjectId(String idPresent, String idGiven) {
        return Arrays.stream(idPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(idGiven, words));
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
        return testName(project) && testRepository(project) && testClientId(project)
                && testClientName(project) && testProjectId(project);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((ProjectContainsKeywordsPredicate) other).nameKeywords) //state checks
                && repositoryKeywords.equals(((ProjectContainsKeywordsPredicate) other).repositoryKeywords)
                && clientNameKeywords.equals(((ProjectContainsKeywordsPredicate) other).clientNameKeywords)
                && clientIdKeywords.equals(((ProjectContainsKeywordsPredicate) other).clientIdKeywords)
                && projectIdKeywords.equals(((ProjectContainsKeywordsPredicate) other).projectIdKeywords));
    }
}
