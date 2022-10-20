package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Class} matches the date given.
 */
public class ClassContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ClassContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWord(person.getAClass().classDateTime, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClassContainsKeywordsPredicate) other).keywords)); // state check
    }

}
