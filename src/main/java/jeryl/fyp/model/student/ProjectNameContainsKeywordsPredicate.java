package jeryl.fyp.model.student;

import java.util.List;

import jeryl.fyp.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code ProjectName} matches any of the keywords given.
 */
public class ProjectNameContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public ProjectNameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(
                        student.getProjectName().fullProjectName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProjectNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
