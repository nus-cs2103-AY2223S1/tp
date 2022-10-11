package jeryl.fyp.model.student;

import java.util.List;
import java.util.function.Predicate;

import jeryl.fyp.commons.util.StringUtil;


/**
 * Tests that a {@code Student}'s {@code ProjectName} matches any of the keywords given.
 */
public class ProjectNameContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    /**
     * Initialise ProjectNameContainsKeywordsPredicate with the given keywords for searching.
     * @param keywords The keyword list the user wants to search.
     */
    public ProjectNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(student.getProjectName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProjectNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
