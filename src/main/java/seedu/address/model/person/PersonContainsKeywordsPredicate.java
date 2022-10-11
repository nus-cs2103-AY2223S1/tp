package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Fields} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAnyIgnoreCase(person.getName().fullName, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAnyIgnoreCase(person.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}
