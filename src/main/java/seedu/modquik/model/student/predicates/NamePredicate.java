package seedu.modquik.model.student.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.modquik.commons.util.StringUtil;
import seedu.modquik.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NamePredicate implements Predicate<Student> {
    private final List<String> keywords;

    public NamePredicate(List<String> keywords) {
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
                || (other instanceof NamePredicate // instanceof handles nulls
                && keywords.equals(((NamePredicate) other).keywords)); // state check
    }

}
