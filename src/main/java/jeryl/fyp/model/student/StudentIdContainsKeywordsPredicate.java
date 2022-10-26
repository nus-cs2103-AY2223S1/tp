package jeryl.fyp.model.student;

import java.util.List;

import jeryl.fyp.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code StudentId} matches any of the keywords given.
 */
public class StudentIdContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public StudentIdContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(
                        student.getStudentId().id, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentIdContainsKeywordsPredicate) other).keywords)); // state check
    }
}
