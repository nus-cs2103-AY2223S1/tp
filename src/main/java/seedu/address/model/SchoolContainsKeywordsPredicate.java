package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;

/**
 * Tests that a {@code Student}'s {@code School} matches any of the keywords given.
 */
public class SchoolContainsKeywordsPredicate<T> implements Predicate<T> {
    private final List<String> keywords;

    public SchoolContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof Student) {
            Student student = (Student) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getSchool().school, keyword));
        }
        throw new ClassCastException("SchoolContainsKeyword predicate can only be applied to Person.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SchoolContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SchoolContainsKeywordsPredicate) other).keywords)); // state check
    }

}
