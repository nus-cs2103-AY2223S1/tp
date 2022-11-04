package seedu.hrpro.model.project;

import java.util.List;
import java.util.function.Predicate;

import seedu.hrpro.commons.util.StringUtil;

/**
 * Tests that a {@code Project}'s {@code ProjectName} matches any of the keywords given.
 */
public class ProjectNameContainsKeywordsPredicate implements Predicate<Project> {
    private final List<String> keywords;

    public ProjectNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Project project) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsNameIgnoreCase(project.getProjectName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProjectNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
