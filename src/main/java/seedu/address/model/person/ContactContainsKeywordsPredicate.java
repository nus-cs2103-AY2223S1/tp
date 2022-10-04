package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ContactContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContactContainsKeywordsPredicate) other).keywords)); // state check
    }

}
