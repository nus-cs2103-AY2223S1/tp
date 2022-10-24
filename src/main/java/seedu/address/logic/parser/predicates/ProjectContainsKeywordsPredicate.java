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

    public ProjectContainsKeywordsPredicate(List<String> nameKeywords, List<String> repositoryKeywords) {
        this.nameKeywords = nameKeywords;
        this.repositoryKeywords = repositoryKeywords;
    }

    public boolean testName(String namePresent, String nameGiven) {
        return Arrays.stream(namePresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(nameGiven, words));
    }

    public boolean testRepository(String repoPresent, String repoGiven) {
        return Arrays.stream(repoPresent.trim().split("\\s+"))
                .anyMatch(words -> StringUtil.containsWordIgnoreCase(repoGiven, words));
    }

    public boolean testName(Project project) {
        if (nameKeywords.isEmpty()) {
            return true;
        } else {
            return nameKeywords.stream().anyMatch(name -> testName(name, project.getProjectName().toString()));
        }
    }

    public boolean testRepository(Project project) {
        if (repositoryKeywords.isEmpty()) {
            return true;
        } else {
            return repositoryKeywords.stream().anyMatch(name -> testName(name, project.getRepository().toString()));
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
