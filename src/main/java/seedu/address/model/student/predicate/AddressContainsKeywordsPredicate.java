package seedu.address.model.student.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Address} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getAddress().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AddressContainsKeywordsPredicate) other).keywords)); // state check
    }

}
