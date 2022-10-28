package seedu.address.model.person.predicate;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the phone given.
 */
public class PhoneContainsNumberPredicate implements Predicate<Person> {
    private String keyword;

    public PhoneContainsNumberPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWord(person.getPhone().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsNumberPredicate // instanceof handles nulls
                && keyword.equals(((PhoneContainsNumberPredicate) other).keyword)); // state check
    }
}
