package jeryl.fyp.model.student;

import java.util.List;

import jeryl.fyp.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code StudentName} matches any of the keywords given.
 */
public class StudentNameContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {

    public StudentNameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(
                        student.getStudentName().fullStudentName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
