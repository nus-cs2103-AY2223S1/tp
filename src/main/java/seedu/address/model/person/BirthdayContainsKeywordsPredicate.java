package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Birthday} matches any of the keywords given.
 */
public class BirthdayContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public BirthdayContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getBirthday().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BirthdayContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BirthdayContainsKeywordsPredicate) other).keywords)); // state check
    }

}
