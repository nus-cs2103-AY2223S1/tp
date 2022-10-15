package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class StudentContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public StudentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentContainsKeywordsPredicate) other).keywords)); // state check
    }

}
