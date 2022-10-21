package jeryl.fyp.model.student;

import java.util.List;
import java.util.function.Predicate;

import jeryl.fyp.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code StudentName} matches any of the keywords given.
 */
public class StudentNameContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public StudentNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(student
                        .getStudentName().fullStudentName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
