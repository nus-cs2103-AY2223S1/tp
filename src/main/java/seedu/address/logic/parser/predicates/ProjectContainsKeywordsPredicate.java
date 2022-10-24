package seedu.address.logic.parser.predicates;

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
    public boolean testName(Project project) {
        return nameKeywords.isEmpty() ? true : nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(project.getProjectName().toString(), keyword));
    }

    public boolean testRepository(Project project) {
        return repositoryKeywords.isEmpty() ? true :repositoryKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(project.getRepository().toString(), keyword));
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
