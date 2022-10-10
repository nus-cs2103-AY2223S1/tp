package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class ClassPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public ClassPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getClassName().className, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassPredicate // instanceof handles nulls
                && keywords.equals(((ClassPredicate) other).keywords)); // state check
    }

}
