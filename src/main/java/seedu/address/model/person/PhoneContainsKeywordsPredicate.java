package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}

