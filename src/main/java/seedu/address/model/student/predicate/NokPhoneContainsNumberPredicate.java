package seedu.address.model.student.predicate;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.student.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the phone given.
 */
public class NokPhoneContainsNumberPredicate implements Predicate<Person> {
    private String keyword;

    public NokPhoneContainsNumberPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWord(person.getNokPhone().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NokPhoneContainsNumberPredicate // instanceof handles nulls
                && keyword.equals(((NokPhoneContainsNumberPredicate) other).keyword)); // state check
    }
}
