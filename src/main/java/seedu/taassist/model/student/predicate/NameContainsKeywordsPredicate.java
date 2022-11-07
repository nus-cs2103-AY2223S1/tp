package seedu.taassist.model.student.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.taassist.commons.util.StringUtil;
import seedu.taassist.model.student.Student;

/**
 * Tests if a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    /**
     * Returns a predicate that checks if a student's name contains any of the keywords in {@code keywords}.
     *
     * @param keywords Keywords to find in a student's name.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
