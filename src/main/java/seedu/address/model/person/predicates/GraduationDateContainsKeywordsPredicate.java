package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code GraduationDate} matches any of the keywords given.
 */
public class GraduationDateContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public GraduationDateContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getGraduationDate().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GraduationDateContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GraduationDateContainsKeywordsPredicate) other).keywords)); // state check
    }

}
